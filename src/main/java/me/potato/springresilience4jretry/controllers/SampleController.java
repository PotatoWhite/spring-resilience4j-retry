
/*
 * SampleController.java 2022. 01. 11
 *
 * Copyright 2022 Naver Cloud Corp. All rights Reserved.
 * Naver Cloud Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package me.potato.springresilience4jretry.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dongju.paek
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class SampleController {
	private final RetryRegistry retryRegistry;
	private int count = 0;

	@GetMapping("/test01")
	public String test01() {
		var sampleRetry = retryRegistry.retry("sampleRetry");
		var aSupply = Retry.decorateCheckedSupplier(sampleRetry, () -> {
			count++;
			log.info("attempt {}", count);
			if (count % 3 > 0)
				throw new RuntimeException("an error");

			return "Hello world"  + count;
		});
		return Try.of(aSupply).recover(throwable -> "Hello recovery" + count).get();
	}


}