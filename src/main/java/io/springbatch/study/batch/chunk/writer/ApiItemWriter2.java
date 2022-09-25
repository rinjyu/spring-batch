package io.springbatch.study.batch.chunk.writer;

import io.springbatch.study.batch.domain.ApiRequestVO;
import io.springbatch.study.batch.domain.ApiResponseVO;
import io.springbatch.study.service.AbstractApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

@Slf4j
public class ApiItemWriter2 extends FlatFileItemWriter<ApiRequestVO> {

    private AbstractApiService apiService;

    public ApiItemWriter2(AbstractApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void write(List<? extends ApiRequestVO> items) throws Exception {

        System.out.println("----------------------------------");
        items.forEach(item -> System.out.println("items = " + item));
        System.out.println("----------------------------------");

        ApiResponseVO response = apiService.service(items);
        System.out.println("response = " + response);

        items.forEach(item -> item.setApiResponseVO(response));

        super.setResource(new FileSystemResource("D://workspace//project//spring-batch//src//main//resources//product2.txt"));
        super.open(new ExecutionContext());
        super.setLineAggregator(new DelimitedLineAggregator<>());
        super.setAppendAllowed(true);
        super.write(items);
    }
}
