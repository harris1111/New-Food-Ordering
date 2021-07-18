set IN_DIR=./
set OUT_DIR=../src/
set BUILD_FILES=MessageWrapper.proto
protoc -IPATH=%IN_DIR% --java_out=%OUT_DIR% %BUILD_FILES%