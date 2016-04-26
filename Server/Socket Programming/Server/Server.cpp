#include "server.h"
#include <opencv2/opencv.hpp>
#include <vector>

using namespace cv;

SOCKET sock_LISTEN;
SOCKET sock_CONNECTION;

uchar toBeSent[864000];
long SUCCESSFUL;
WSAData WinSockData;
WORD DLLVERSION;
SOCKADDR_IN ADDRESS;



void transmitString()
{
	SUCCESSFUL = send(sock_CONNECTION, "You are connected to banana server\n", 36, NULL);
	std::cout << "Message sent successfuly\n" << std::endl;
}

void main()
{
	DLLVERSION = MAKEWORD(2, 1);
	SUCCESSFUL = WSAStartup(DLLVERSION, &WinSockData);
	int addressSize = sizeof(ADDRESS);

	

	sock_CONNECTION = socket(AF_INET, SOCK_STREAM, NULL);
	ADDRESS.sin_addr.s_addr = inet_addr("127.0.0.1");
	ADDRESS.sin_family = AF_INET;
	ADDRESS.sin_port = htons(444);

	sock_LISTEN = socket(AF_INET, SOCK_STREAM, NULL);
	bind(sock_LISTEN, (SOCKADDR*)&ADDRESS, sizeof(ADDRESS));
	listen(sock_LISTEN, SOMAXCONN);
	cv::Mat image = cv::imread("C:\\Users\\Ahmed Abodeif\\Desktop\\test.jpg");
	VideoCapture cap = VideoCapture("C:\\Users\\Ahmed Abodeif\\Desktop\\out.mp4");
	//cv::namedWindow("VideoPlayBack", CV_WINDOW_AUTOSIZE);
	//cv::imshow("VideoPlayBack", image);
	//waitKey(0);
	
	std::cout << "SERVER: Waiting for incoming connection....";
	/*
	std::vector<unsigned char> buffer;
	std::vector<int> params = { 1 };
	imencode(".jpg", image, buffer);
	imwrite("C:\\Users\\Ahmed Abodeif\\Desktop\\enc.jpg", buffer);
	*/
	

	for (;;)
	{

		
		//imshow("window", image);
		//waitKey(10);
		//cap >> image;
		Mat image;
		
		if (waitKey(30) >= 0) break;
		
		
		
		if (sock_CONNECTION = accept(sock_LISTEN, (SOCKADDR*)&ADDRESS, &addressSize))
		{
			cap >> image;
			imshow("vid", image);

			//std::cout << "\n Connection was established....\n Sending Welcome message\n" << std::endl;
			
			//transmitString();
			
			//std::cout << "\n Connection was found" << std::endl;
			//SUCCESSFUL = send(sock_CONNECTION, "Welcome! you have connected to RoomALive Banana Server.", 46, NULL);
			SUCCESSFUL = send(sock_CONNECTION, reinterpret_cast<const char *>(image.data), 864000, NULL);
			//std::cout << "transmit completed" << std::endl;
			//SUCCESSFUL = send(sock_CONNECTION, image.data, 864000, 0);

			//	after accepting the connection time to do what I want
			
		}
		
		

	}
	
	
}