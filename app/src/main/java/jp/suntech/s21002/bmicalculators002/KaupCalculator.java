package jp.suntech.s21002.bmicalculators002;

public class KaupCalculator {
    float height;
    float weight;
    float age;

    public void setBodyScore(float height, float weight, float age){
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    public float getScore(){
        return weight / (height * height);
    }

    public int getDOD(){
        float score = getScore();
        if(age < 1){
            if(score < 16){
                return 0;
            }else if(score < 18){
                return 1;
            }else{
                return 2;
            }
        }else if(age < 1.5){
            if(score < 15.5){
                return 0;
            }else if(score < 17.5){
                return 1;
            }else{
                return 2;
            }
        }else if(age < 3){
            if(score < 15){
                return 0;
            }else if(score < 17){
                return 1;
            }else{
                return 2;
            }
        }else{
            if(score < 14.5){
                return 0;
            }else if(score < 16.5){
                return 1;
            }else{
                return 2;
            }
        }
    }

    public float getStandardWeight(){
        float standardScore;
        if(age < 1){
            standardScore = 17;
        }else if(age < 2){
            standardScore = 16.5F;
        }else if(age < 3){
            standardScore = 16;
        }else{
            standardScore = 15.5F;
        }
        return standardScore * (height * height);
    }

    public String getColor(){
        switch (this.getDOD()){
            case 0:
                return "blue";
            case 1:
                return "black";
            case 2:
                return "red";
        }
        return "";
    }
}
