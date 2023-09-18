## Word Count Tool

### Build
```
mvn clean build
alias ccwc='java -jar target/wcTool-1.0-SNAPSHOT.jar'
```

### Execute
```
ccwc -c textfile.txt --- number of bytes
  10
ccwc -l textfile.txt --- number of lines
  2
ccwc -2 textfile.txt --- number of words
  10
ccwc -m textfile.txt --- number of characters
  10
```

### Reference
https://codingchallenges.fyi/challenges/challenge-wc
