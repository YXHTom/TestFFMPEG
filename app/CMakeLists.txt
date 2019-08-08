cmake_minimum_required(VERSION 3.4.1)

#ffmpeg库设置
add_library(
        libavcodec
        SHARED
        IMPORTED)
set_target_properties(
        libavcodec
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/src/main/cpp/ffmpeg/${ANDROID_ABI}/libavcodec.so)

add_library(
        libavdevice
        SHARED
        IMPORTED)
set_target_properties(
        libavdevice
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/src/main/cpp/ffmpeg/${ANDROID_ABI}/libavdevice.so)

add_library(
        libavfilter
        SHARED
        IMPORTED)
set_target_properties(
        libavfilter
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/src/main/cpp/ffmpeg/${ANDROID_ABI}/libavfilter.so)

add_library(
        libavformat
        SHARED
        IMPORTED)
set_target_properties(
        libavformat
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/src/main/cpp/ffmpeg/${ANDROID_ABI}/libavformat.so)

add_library(
        libavutil
        SHARED
        IMPORTED)
set_target_properties(
        libavutil
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/src/main/cpp/ffmpeg/${ANDROID_ABI}/libavutil.so)

add_library(
        libswresample
        SHARED
        IMPORTED)
set_target_properties(
        libswresample
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/src/main/cpp/ffmpeg/${ANDROID_ABI}/libswresample.so)

add_library(
        libswscale
        SHARED
        IMPORTED)
set_target_properties(
        libswscale
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/src/main/cpp/ffmpeg/${ANDROID_ABI}/libswscale.so)


include_directories(src/main/cpp/ffmpeg/)
add_library(
        ffmpeg-cmd
        SHARED
        src/main/cpp/ffmpeg/cmdutils.c
        src/main/cpp/ffmpeg/ffmpeg_filter.c
        src/main/cpp/ffmpeg/ffmpeg_opt.c
        src/main/cpp/ffmpeg/ffmpeg_hw.c
        src/main/cpp/ffmpeg/ffmpeg.c
        src/main/cpp/ffmpeg-cmd.cpp)

find_library(
        log-lib
        log)

target_link_libraries(
        ffmpeg-cmd
        libavcodec
        libavdevice
        libavfilter
        libavformat
        libavutil
        libswresample
        libswscale
        -landroid
        ${log-lib})
