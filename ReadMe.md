# SearchHub

SearchHub is a Java-based search platform built using Apache Lucene. The project is designed to explore modern search and information retrieval concepts, including document indexing, full-text search, relevance scoring, and result highlighting.

The goal of the project is to combine traditional search technologies with modern software engineering practices and eventually extend the platform with AI-assisted retrieval and summarization capabilities.

---

## Features

### Current Features

* Recursive indexing of text documents
* Apache Lucene-based search engine
* Full-text search
* Relevance scoring
* Search result highlighting
* Externalized configuration using properties files
* File metadata indexing
* Simple console-based search interface

### Planned Features

* Spring Boot REST API
* PostgreSQL integration
* Search analytics and query tracking
* Elasticsearch/OpenSearch integration
* AI-assisted search summaries
* Retrieval-Augmented Generation (RAG)
* Web-based user interface

---

## Technology Stack

### Current

* Java 17
* Apache Lucene
* Maven

### Planned

* Spring Boot
* PostgreSQL
* Elasticsearch/OpenSearch
* Docker
* AWS
* OpenAI / LLM Integration

---

## Project Structure

```text
searchhub/
├── src/
│   ├── index/
│   ├── search/
│   ├── utilities/
│   └── config/
├── lucene-index/
├── pom.xml
└── README.md
```

---

## Configuration

Create a configuration file containing:

```properties
books.path=/path/to/books
index.path=/path/to/lucene-index
```

Example:

```properties
books.path=/Users/neeraj/homeo-books
index.path=/Users/neeraj/searchhub/lucene-index
```

---

## Running the Indexer

The indexing module scans the configured document directory and creates a Lucene index.

Example:

```bash
mvn exec:java
```

or run the indexing class directly from your IDE.

---

## Running Search

Searches are performed against the generated Lucene index.

Example query:

```text
anxiety
```

Example result:

```text
File: materia-medica.txt
Score: 3.42

...patient suffering from [[anxiety]] and restlessness...
```

## Sample Search

Query:
anxiety

Result:
File: materia-medica.txt
Score: 3.42

...patient suffering from [[anxiety]] and restlessness...


##For testing spring-boot search api:

Url : /api/search?query=anxiety


---

## Why This Project?

I have spent more than 10 years working with search-based systems using Apache Lucene and PostgreSQL search technologies. SearchHub was created to refresh and modernize that experience while exploring contemporary search, cloud, and AI-assisted retrieval approaches.

The project focuses on practical information retrieval problems rather than generic CRUD application development.

---

## Roadmap

### Phase 1

* Document indexing
* Search
* Highlighting

### Phase 2

* Spring Boot REST API
* PostgreSQL integration
* Search analytics

### Phase 3

* Elasticsearch/OpenSearch
* AI-assisted summaries
* Retrieval-Augmented Generation (RAG)

### Phase 4

* AWS deployment
* Public demo environment

---

## Author

Neeraj Kumar Shah

Technical Lead | Search Solutions Specialist | Java | Spring Boot | Apache Lucene | PostgreSQL | AWS
