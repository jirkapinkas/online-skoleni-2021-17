package com.example.mailing.repository;

import com.example.api.rest.CustomerRestApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "crm", url = "http://crm:8080")
public interface CrmRepository extends CustomerRestApi {

}
