#include <opencv2/opencv.hpp>
#include "Client.h"


using namespace std;


//	The client class is only used to ensure that the server class is functional, After proving server works
//	this class will be removed and sed in android.
void main()
{
	long SUCCESSFUL;
	WSAData WinSockData;
	WORD DLLVersion;
	DLLVersion = MAKEWORD(2, 1);
	SUCCESSFUL = WSAStartup(DLLVersion, &WinSockData);

	string RESPONSE;
	uchar* CONVERTER;
	char MESSAGE[864000];
	char text[34];

	SOCKADDR_IN ADDRESS;
	SOCKET sock;

	sock = socket(AF_INET, SOCK_STREAM, NULL);

	ADDRESS.sin_addr.s_addr = inet_addr("127.0.0.1");
	ADDRESS.sin_family = AF_INET;
	ADDRESS.sin_port = htons(444);

	cout << "tCLINET: Do you wnat to connect to the Server? (Y/N) ";
	cin >> RESPONSE;

	RESPONSE[0] = tolower(RESPONSE[0]);

	if (RESPONSE == "n")
	{
		cout << "QUITTING APPLICATION";
	}
	else if (RESPONSE == "y")
	{
		connect(sock, (SOCKADDR*)&ADDRESS, sizeof(ADDRESS));
		recv(sock, text, sizeof(text), NULL);
		cout << text << endl;
		/*
		SUCCESSFUL = recv(sock, MESSAGE, sizeof(MESSAGE), NULL);
		CONVERTER = reinterpr
		et_cast<uchar *> (MESSAGE);
		// convert message to mat
		cv::Mat tmp = cv::Mat(400, 720, CV_8UC3, CONVERTER);
		cv::namedWindow("ServerImage", CV_WINDOW_AUTOSIZE);
		cv::imshow("ServerImage", tmp);
		cvWaitKey(0);
		cout << "\n\tMessage from server:\n\n\t" << CONVERTER << endl;
		*/
	}
	else
	{
		cout << "Invalid Command" << endl;
	}

	system("PAUSE");
	exit(1);
}