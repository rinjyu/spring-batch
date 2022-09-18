package io.springbatch.study.listener;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.AfterChunkError;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class CustomChunkListener {

    private int i;

    @BeforeChunk
    public void beforeChunk(ChunkContext chunkContext) {
        System.out.println(">> beforeChunk : "+ chunkContext.getStepContext().getStepExecution().getStepName());
        Object count = chunkContext.getStepContext().getStepExecution().getExecutionContext().get("count");
        if(count == null){
            chunkContext.getStepContext().getStepExecution().getExecutionContext().putInt("count", ++i);
        }
    }

    @AfterChunk
    public void afterChunk(ChunkContext chunkContext) {
        System.out.println(">> afterChunk : "+ chunkContext.getStepContext().getStepExecution().getStepName());
        int count = (int)chunkContext.getStepContext().getStepExecution().getExecutionContext().get("count");
        System.out.println(">> count : "+ count);
    }

    @AfterChunkError
    public void afterChunkError(ChunkContext chunkContext) {
        System.out.println(">> afterChunkError : "+ chunkContext.getStepContext().getStepExecution().getStepName());
        int count = (int)chunkContext.getStepContext().getStepExecution().getExecutionContext().get("count");
        System.out.println(">> count : "+ count);
    }
}
