package jp.suntech.s21002.bmicalculators002;

public class BMICalculator {
    float height;
    float weight;

    public void setBodyScore(float height, float weight){
        this.height = height;
        this.weight = weight;
    }

    public float getScore(){
        return weight / (height * height);
    }

    public int getDOD(){
        float score = getScore();
        if(score < 18.5){
            return 0;
        }else if(score < 25){
            return 1;
        }else if(score < 30){
            return 2;
        }else if(score < 35){
            return 3;
        }else if(score < 40){
            return 4;
        }else{
            return 5;
        }
    }

    public float getStandardWeight(){
        return 22 * (height * height);
    }


    public String getColor(){
        switch (this.getDOD()){
            case 0:
                return "blue";
            case 1:
                return "black";
            case 2:
            case 3:
            case 4:
            case 5:
                return "red";
        }
        return "";
    }
}
