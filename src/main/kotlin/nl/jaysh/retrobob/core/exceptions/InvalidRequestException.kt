package nl.jaysh.retrobob.core.exceptions

class InvalidRequestException(val subject: String, val violation: String) : RuntimeException("$subject: $violation")
