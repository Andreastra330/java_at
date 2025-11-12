package ru.stqa.mantis.model;

public record UserData(String username,String email,String password,String realName) {

    public UserData(){
        this("","","password","");
    }

    public UserData withUsername(String username){
        return new UserData(username,this.email,this.password,this.realName);
    }

    public UserData withEmail(String email){
        return new UserData(this.username,email,this.password,this.realName);
    }

    public UserData withPassword(String password){
        return new UserData(this.username,this.email,password,this.realName);
    }

    public UserData withRealName(String realName){
        return new UserData(this.username,this.email,this.password,realName);
    }
}
