package ch.jb.bloq.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException: RuntimeException {
    private var resourceName: String
    private var fieldName: String
    private var fieldValue: Any

    constructor(
            resourceName: String,
            fieldName: String,
            fieldValue: Any
    ): super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue)) {
        this.resourceName = resourceName
        this.fieldName = fieldName
        this.fieldValue = fieldValue
    }
}