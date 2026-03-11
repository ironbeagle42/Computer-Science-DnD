public class MarkovPredictorTester {
    public static void main(String[] args) {
        MarkovPredictor predictor = new MarkovPredictor();
        predictor.readData("weather.csv");
        System.out.println(predictor.predictNextState("Cloudy"));
        System.out.println(predictor.predictNextState("Cloudy"));
        System.out.println(predictor.predictNextState("Cloudy"));
        System.out.println(predictor.predictNextState("Cloudy"));
        System.out.println(predictor.predictNextState("Cloudy"));
        System.out.println(predictor.predictNextState("Cloudy"));
        System.out.println(predictor.predictNextState("Cloudy"));
        System.out.println(predictor.predictNextState("Rainy"));
        System.out.println(predictor.predictNextState("Rainy"));
        System.out.println(predictor.predictNextState("Rainy"));
        System.out.println(predictor.predictNextState("Sunny"));
        System.out.println(predictor.predictNextState("Sunny"));
        System.out.println(predictor.predictNextState("Sunny"));
        System.out.println(predictor.predictNextState("Sunny"));

        MarkovPredictor predictor2 = new MarkovPredictor();
        predictor2.readData("activites.csv");
        System.out.println(predictor2.predictNextState("Sleeping"));
        System.out.println(predictor2.predictNextState("Sleeping"));
        System.out.println(predictor2.predictNextState("Sleeping"));
        System.out.println(predictor2.predictNextState("Sleeping"));
        System.out.println(predictor2.predictNextState("Eating"));
        System.out.println(predictor2.predictNextState("Eating"));
        System.out.println(predictor2.predictNextState("Eating"));
        System.out.println(predictor2.predictNextState("Working"));
        System.out.println(predictor2.predictNextState("Working"));
        System.out.println(predictor2.predictNextState("Working"));
        System.out.println(predictor2.predictNextState("Working"));
    }
}
