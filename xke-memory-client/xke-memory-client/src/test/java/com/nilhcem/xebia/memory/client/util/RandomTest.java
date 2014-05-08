package com.nilhcem.xebia.memory.client.util;

import org.junit.Assert;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class RandomTest {

    @Test
    public void verify_inclusivity_of_the_random() {
        assertThat(Random.getBetweenInclusive(4, 4)).isEqualTo(4);
    }

    @Test
    public void should_give_number_between_range() {
        // Given
        int min = 12;
        int max = 42;

        // When
        int res1 = Random.getBetweenInclusive(min, max);
        int res2 = Random.getBetweenInclusive(min, max);
        int res3 = Random.getBetweenInclusive(min, max);

        // Then
        assertThat(res1).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max);
        assertThat(res2).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max);
        assertThat(res3).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max);
    }

    @Test
    public void should_not_always_return_the_same_number() {
        // Given
        int min = 0;
        int max = 99999;

        int initialValue = Random.getBetweenInclusive(min, max);
        for (int i = 0; i < 10000; i++) {
            int value = Random.getBetweenInclusive(min, max);
            if (initialValue != value) {
                return;
            }
        }
        Assert.fail();
    }
}
