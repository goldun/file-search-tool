# file-search-tool

This tool allows you to index files in the directory and then quickly find any file, that contains text you are interesting in.

## Usage

### Pre-requirements
* It is expected to be run on *unix systems.
* You'll need to install 
  * java 8
  * maven
  * docker (or mysql as an alternative) https://docs.docker.com/engine/install/ubuntu/
  
### Build
```
mvn clean install
```
### Run
 In order to start MySql docker container
```
mvn docker:build
mvn docker:start
```
Alternatively you'll need to setup DB manually.

In such case don't forget to create corresponding user `trik/3kon` and then use `init-db.sql`

In order to start the app

```
mvn spring-boot:start
```

### Terminate
Do not forget to stop docker container, after you are done
```
mvn docker:stop
```