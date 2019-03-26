LIB_DIR = lib
TEST_DIR = test
SRC_DIR = src
BUILD_DIR = build
ASSET_DIR = assets

# java class path
JC = $(LIB_DIR)/*:$(SRC_DIR):$(BUILD_DIR)

FILES = Http Server Post PostDB MemoryPostDB
CLASSES = $(patsubst %, $(SRC_DIR)/%.class, $(FILES))

.PHONY: all
all: $(CLASSES)
	cp $(SRC_DIR)/*.class $(BUILD_DIR)

.PHONY: serve
serve: all
	java -cp $(JC) Main $(ASSET_DIR)

%.class: %.java
	javac -cp $(JC) $<

.PHONY: clean
clean:
	rm -r build/* src/*.class
