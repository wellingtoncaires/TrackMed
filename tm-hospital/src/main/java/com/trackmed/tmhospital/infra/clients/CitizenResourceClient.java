package com.trackmed.tmhospital.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "tm-citizen", path = "v1/cidadao")
public interface CitizenResourceClient {


}
