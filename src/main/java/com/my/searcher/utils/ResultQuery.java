package com.my.searcher.utils;

import java.io.Serializable;
import java.util.List;

import com.my.searcher.dto.response.SearchResult;

public class ResultQuery implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5209378109633113770L;
	private Float timeTaken;
    private Integer numberOfResults;
    private List<SearchResult> results;

    public ResultQuery() {
    }

    public ResultQuery(Float timeTaken, Integer numberOfResults, List<SearchResult> results) {
        this.timeTaken = timeTaken;
        this.numberOfResults = numberOfResults;
        this.results = results;
    }

    public Float getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Float timeTaken) {
        this.timeTaken = timeTaken;
    }

    public Integer getNumberOfResults() {
        return numberOfResults;
    }

    public void setNumberOfResults(Integer numberOfResults) {
        this.numberOfResults = numberOfResults;
    }


	public List<SearchResult> getResults() {
		return results;
	}

	public void setResults(List<SearchResult> results) {
		this.results = results;
	}

}