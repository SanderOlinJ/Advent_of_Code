cmake_minimum_required(VERSION 3.25)
project(day03)

set(CMAKE_CXX_STANDARD 17)

add_executable(day03 gear_ratios_01.cpp gear_ratios_02.cpp main.cpp gear_ratios_01.h gear_ratios_02.h common_structs.h)

set_target_properties(day03 PROPERTIES
        RUNTIME_OUTPUT_DIRECTORY "${CMAKE_SOURCE_DIR}"
        )