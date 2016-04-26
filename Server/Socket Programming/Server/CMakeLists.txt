cmake_minimum_required(VERSION 2.8)
# based on https://github.com/MasteringOpenCV/code/tree/master/Chapter2_iPhoneAR/Example_MarkerBasedAR/Example_MarkerBasedAR

project(ServerProj)
SET(BUILD_SHARED_LIBS ON)


find_package(OpenCV REQUIRED)
include_directories( ${OpenCV_INCLUDE_DIRS} Server.h)
set(srcs Server.cpp)
add_executable(ServerProj ${srcs})
target_link_libraries(ServerProj LINK_PUBLIC ${OpenCV_LIBS})