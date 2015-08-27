package com.squareup.javapoet;

import java.io.IOException;

import static com.squareup.javapoet.Util.checkNotNull;

/**
 * Created by tiran on 8/27/15.
 */
public class StaticImportSpec {
    public ClassName typeName;
    public String methodName;

    public StaticImportSpec(Builder builder) {
        this.typeName = builder.typeName;
        this.methodName = builder.methodName;
    }

    public static Builder builder(ClassName type) {
        checkNotNull(type, "type == null");
        return new Builder(type);
    }

    public void emit(CodeWriter codeWriter) throws IOException {
        codeWriter.emit("import static $L.$N;", typeName, methodName);
    }

    public static final class Builder {
        private final ClassName typeName;
        private String methodName = "*";

        private Builder(ClassName typeName) {
            this.typeName = typeName;
        }

        public Builder setMethod(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public StaticImportSpec build() {
            return new StaticImportSpec(this);
        }
    }
}
