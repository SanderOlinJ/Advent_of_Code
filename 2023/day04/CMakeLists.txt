cmake_minimum_required(VERSION 3.25)
project(day04 C)

set(CMAKE_C_STANDARD 11)

add_executable(day04 main.c scratchcards.h scratchcards_01.c scratchcards_02.c)

set_target_properties(day04 PROPERTIES
        RUNTIME_OUTPUT_DIRECTORY "${CMAKE_SOURCE_DIR}"
        )
