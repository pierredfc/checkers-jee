# Checkers game for JEE labs [![Build Status](https://travis-ci.org/pierredfc/checkers-jee.svg?branch=master)](https://travis-ci.org/pierredfc/checkers-jee)


## Getting started

### Prerequisites

* `Java 8` should be installed.
* `Maven` and `glassfish` plugin should be installed.

You can find three maven projects:
* __checkers-core__ for the checkers core game
* __checkers-jpa__ for the JPA handler
* __checkers-web__ for the API using JAX-RS

### Installation

Checkers is installed by running the following commands in your terminal.

```shell
# Clone the repository...
git clone https://github.com/pierredfc/checkers-jee.git

# Change directory to our repository
cd checkers-jee/

# Clean and install the repository
mvn clean install
```

## Using Checkers

In order to use checkers, you can run the following command in your terminal.

```shell
# Change directory to our API server
cd checkers-web/

# Launch the server using glassfish
mvn glassfish:run
```

## Front

Source code is available at https://github.com/patapizza05/checkers-front. Built with Angular2. 
