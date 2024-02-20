package pl.javastart;

record Person(String firstName, String lastName, int age) {
    String toJson() {
        return STR."""
                {
                    "firstName": "\{firstName}",
                    "lastName": "\{lastName}",
                    "age": "\{age}"
                }
                """;
    }

    String toXml() {
        return STR."""
                <person>
                    <firstName>\{firstName}</firstName>
                    <lastName>\{lastName}</lastName>
                    <age>\{age}</age>
                </person>
                """;
    }
}
