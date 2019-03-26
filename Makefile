ROOT_DIR = $(shell git rev-parse --show-toplevel)
LIB_DIR = $(ROOT_DIR)/lib
TEST_DIR = $(ROOT_DIR)/test
SRC_DIR = $(ROOT_DIR)/src

%.class: %.java
	javac -cp $(LIB_DIR)/*:. $<
