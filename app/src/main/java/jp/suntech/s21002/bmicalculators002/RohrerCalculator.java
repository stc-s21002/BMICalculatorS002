package jp.suntech.s21002.bmicalculators002;

public class RohrerCalculator {
    float height;
    float weight;

    public void setBodyScore(float height, float weight){
        this.height = height;
        this.weight = weight;
    }

    public float getScore(){
        return weight / (height * height * height) * 10;
    }

    public int getDOD(){
        float score = getScore();
        if(score < 100){
            return 0;
        }else if(score < 115){
            return 1;
        }else if(score < 145){
            return 2;
        }else if(score < 160){
            return 3;
        }else{
            return 4;
        }
    }

    public float getStandardWeight(){
        return 130 * (height * height * height) / 10;
    }

    public String getColor(){
        switch (this.getDOD()){
            case 0:
            case 1:
                return "blue";
            case 2:
                return "black";
            case 3:
            case 4:
                return "red";
        }
        return "";
    }
}
