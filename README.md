Labrador
====

Labrador is a dead simple crawler based on crawler4j.
(Actually it's a fetcher with configurations you can easily manipulate._

#### How to configure?
Configurations are stored in directory ```data```:
+ ```data/config/crawler.properties```: misc configurations
+ ```data/config/domains.txt```: line-by-line domains to crawl
+ ```data/config/exclude.txt```: regular expressions to exclude Urls 
+ ```data/config/seeds.txt```: several urls to initialize crawling processes

#### How to run?
You can find Labrador binary in directory ```dist```.
To run it, simply issue commands:
```shell 
java -jar labrador-assembly-1.0.jar
```
or
```shell 
nohup java -jar labrador-assembly-1.0.jar  > runtime.log &
```
#### How to read the output?
The output will be stored in directory ```data/storage``` (each domain has its own directory)

For example: all pages fetched from ```http://vnexpress.net``` will be stored in ```data/storage/vnexpress.net```

To get Html pages downloaded for a specific domain, you must know that:

+ In the domain directory, there's a file named ```site.info``` storing downloaded urls in separated lines

+ Lines' indices will be used to name files storing associated pages
 
Below is the pseudo code to extract downloaded content:

```python
domain_directory = 'data/storage/vnexpress.net/'
info = read_file(domain_directory + 'site.info')
index = 0
for url in info.get_lines():
    content = read_file(domain_directory + index.to_string())
    print "url = %s with content = %s" % (url, content)
```

#### How to rebuild Labrador?
Labrador is written in Scala. To rebuild, please use SBT:

```shell
sbt assembly
```