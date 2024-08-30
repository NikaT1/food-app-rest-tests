package com.testing.tasks.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@RequiredArgsConstructor
@Getter
@Builder
@Jacksonized
@EqualsAndHashCode
public class Product {
    private final String name;
    private final String type;
    private final boolean exotic;
}