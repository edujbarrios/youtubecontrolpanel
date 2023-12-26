package youtube.controlpanel.model.strategies;

/**
 * The DataRetrievalStrategy interface for the Strategy pattern.
 * This interface declares a method for retrieving data from a given URL.
 * It is intended to be implemented by different strategies for data retrieval.
 */
public interface DataRetrievalStrategy {

    /**
     * Retrieves data based on the provided URL.
     *
     * @param url The URL from which the data needs to be retrieved.
     * @return An Object containing the retrieved data. The actual type of the returned
     *         object will depend on the specific implementation of the strategy.
     */
    Object retrieveData(String url);
}
