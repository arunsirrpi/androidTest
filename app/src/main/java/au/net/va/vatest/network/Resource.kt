package au.net.va.vatest.network

class Resource {

    fun getResponse() =
        this::class.java.getResource("/ApiResponse.json")?.readText() ?: "{}"

}