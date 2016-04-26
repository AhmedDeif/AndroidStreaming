cmake_minimum_required(VERSION 2.8)
# based on https://github.com/MasteringOpenCV/code/tree/master/Chapter2_iPhoneAR/Example_MarkerBasedAR/Example_MarkerBasedAR

project(ClientProj)
SET(BUILD_SHARED_LIBS ON)


find_package(OpenCV REQUIRED)
include_directories( ${OpenCV_INCLUDE_DIRS} Client.h)
set(srcs Client.cpp)
add_executable(ClientProj ${srcs})
target_link_libraries(ClientProj LINK_PUBLIC ${OpenCV_LIBS})

