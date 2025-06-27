import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.io.File;
import java.util.List;

public class RecommendationEngine {
    public static void main(String[] args) {
        try {
            // Step 1: Load Data
            DataModel model = new FileDataModel(new File("data.csv"));

            // Step 2: Compute Similarity
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Step 3: Find Nearest Neighbors
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

            // Step 4: Create Recommender
            GenericUserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            // Step 5: Recommend for user ID 1
            List<RecommendedItem> recommendations = recommender.recommend(1, 3);
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Recommended item: " + recommendation.getItemID() + 
                                   " with value: " + recommendation.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}