mvn test# pact-nested-json-handling

## How to parse nested JSON in Java?
A JSON object is an unordered set of key/value pairs. A JSON array is an ordered collection of values. The values could be objects or arrays. Nested JSON is a JSON file with its values being other JSON objects. Accessing nested JSON objects is very much like accessing nested arrays.

## Advantage of PactDslJson
* Best of all, the effort to create and maintain pacts is significantly lower than integration tests.
* Creating pacts requires that you define your consumer use cases, which is something that you have to do already to some degree.
* By codifying those use cases in the form of Pact tests, you get documentation and automated verification more or less for free.
* Pact has become a standard part of our workflow when developing new service interactions. Being able to have contracts that are kept up to date with the code, while simultaneously being able to verify those contracts automatically and in isolation, is a huge benefit.

## Run the contract test using below maven command

``` mvn clean install ```