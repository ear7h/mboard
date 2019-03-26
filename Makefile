ROOT_DIR = $(shell git rev-parse --show-toplevel)
LIB_DIR = $(ROOT_DIR)/lib
TEST_DIR = $(ROOT_DIR)/test
SRC_DIR = $(ROOT_DIR)/src
BUILD_DIR = $(ROOT_DIR)/build

.PHONY: all
all: $(CLASSES:%=%.class)
	mv $(SRC_DIR)/*.class $(BUILD_DIR)

%.class: %.java
	javac -cp $(LIB_DIR)/*:. $<

.PHONY: clean
clean:
	rm -r build/* src/*.class
