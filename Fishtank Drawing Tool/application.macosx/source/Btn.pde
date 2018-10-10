class Btn{ 
  int row;
  int col;
  int ht;
  int wt;
  color c1;
  color c2;
  String text;
  
  Btn(int col, int row, int wt, int ht, color c1, color c2, String text){
    this.col = col;
    this.row = row;
    this.wt = wt;
    this.ht = ht;
    this.c1 = c1;
    this.c2 = c2;
    this.text = text;
  }
  
  void drawBtn(){
      fill(c1);
      rect(col,row,wt,ht);
      fill(c2);
      textSize(24);
      text(text,col + (ht / 2), row + 32);
  }
  
  void scatterFish(){
    for(Fish f: fishes){
      f.row = (int) (random(598 - f.ht));
      f.col = (int) (random(720 - f.ht));
    }
  }
  
  void clr() {
    foods.clear();
    foods.add(new Food(0, 0, whitet, false));
    initBG();
    clear = (clear + 1) % 2;
    
  }

  boolean isClicked(){
    return(mouseX >= col && mouseX <= col + wt && mouseY >= row && 
    mouseY <= row + ht);
  }
  
  void foodColor(color newColor, int type) {
    foods.get(foods.size() - 1).c = newColor;
    foods.get(foods.size() - 1).type = type;
    activeFoodColor = newColor;
    activeFoodType = type;
  }
}
  
