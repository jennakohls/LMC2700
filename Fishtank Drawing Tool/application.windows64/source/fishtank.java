import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.concurrent.CopyOnWriteArrayList; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class fishtank extends PApplet {

/**
How to use:
- draw by feeding the fish! each fish leaves a colored trail when it swims towards food
- click in the black area to drop food. the color of the food determines which fish will swim to eat it. 
- all four fish will swim towards white food. 
- you can switch between two color palettes, RGB and neon
- you can also control the speed at which the fish swim
- once a fish eats the piece of food, it is gone and you will need to drop in more
- press the clear/restart button once to erase the screen and disable the fish
- while cleared, you can drag and drop the fish anywhere you want on the screen
    - for best results, try dragging them by their tails
- then press restart to drop food again
- you can also reset the fish to random positions on the screen by pressing the 'scatter fish' button
**/




//matrix of colors, pv = "pixel value"
int[][] pv = new int[180][150];
//pixel size
int ps = 4;
//speed of fish
int step = ps;

//normal color defs
int black = color(0,0,0);
int gray = color(100,100,100);
int white = color(255,255,255);
int whitet = color(255,255,255,98);
int fishWhite = color(255,255,255, 90);
int fishRed = color(255,0,0, 90);
int red = color(255,0,0);
int fishBlue = color(0,0,255, 90);
int blue = color(0,0,255);
int fishGreen = color(0,255,0, 90);
int green = color(0,255,0);
int darkP = color(103,51,161);
//pinkCoral
int pink1 = color(183,41,239, 98);
//dark blue coral colors
int lblue = color(100,150,254, 98);
int mblue = color(34,101,226, 98);
int dblue = color(52,50,90, 98);
int gblue = color(29,91,153, 98);
//purple coral colors
int lp = color(132, 57, 237);
int dp = color(107,52,170);
//colors with alpha values
int pink1t = color(183,41,239,98);
int lbluet = color(100,150,254, 98);
int mbluet = color(34,101,226,98);
int dbluet = color(52,50,90,98);
int gbluet = color(29,91,153,98);
//food colors
int foodGreen = fishGreen;
int foodRed = fishRed;
int foodBlue = lbluet;

//init food type tracking
final static CopyOnWriteArrayList<Food> foods = new CopyOnWriteArrayList();
int activeFoodColor = whitet;
int activeFoodType = 0;
int activeFood = 0;

int clear = 0;

//init buttons
Btn SFB = new Btn(720, 500, 180, 50, pink1, white, "Scatter Fish");
Btn clrBtn = new Btn(720, 550, 180, 50, gray, white, "Clear");
Btn redFood = new Btn(720, 350, 180, 50, pink1, white, "Color 2");
Btn greenFood = new Btn(720, 400, 180, 50, lp, white, "Color 3");
Btn whiteFood = new Btn(720, 450, 180, 50, white, black, "Color 4");
Btn blueFood = new Btn(720, 300, 180, 50, mblue, white, "Color 1");
Btn RGBBtn = new Btn(720, 250, 180, 50, red, white, "RGB Palette");
Btn neonBtn = new Btn(720, 200, 180, 50, lblue, white, "Neon Palette");
Btn onexSpd = new Btn(720, 50, 180, 50, color(50,50,50), white, "Speed 1x");
Btn twoxSpd = new Btn(720, 100, 180, 50, color(150,150,150), white, "Speed 2x");
Btn fourxSpd = new Btn(720, 150, 180, 50, color(200,200,200), white, "Speed 4x");
Btn halfSpd = new Btn(720, 0, 180, 50, color(25,25,25), white, "Speed 0.5x");

Btn[] buttons = new Btn[] {SFB, clrBtn, redFood, greenFood, whiteFood, blueFood, 
  RGBBtn, neonBtn, onexSpd, twoxSpd, fourxSpd, halfSpd};

//init fishes
Fish f1 = new Fish(90, 190, pink1, 2);
Fish f2 = new Fish(190, 190, mblue, 4);
Fish f3 = new Fish(290, 190, lp, 3);
Fish f4 = new Fish(390, 190, whitet, 1);

Fish[] fishes = new Fish[] {f1,f2, f3, f4};

public void setup() {
  
  noStroke();
  initBG();
  foods.add(new Food(0, 0, activeFoodColor, false));
}
public void draw() {
  frameRate(30);
  drawBG();
  if (clear == 0) {
    clrBtn.text = "Restart";
    for(int i = 0; i < fishes.length; i++) {
      updateFish(fishes[i]);
      fishes[i].drawTrail();
      }
    if(!foods.isEmpty()) {
        for (Food f: foods) {
          updateFood(f);
      }
    }
  }
  if (clear == 1) {
    clrBtn.text = "Clear";
      for(int i = 0; i < fishes.length; i++) {
      fishes[i].drawFish();
      }
  }
}

public void updateFood(Food f) {
  //move active fish
  if(f.active == true && f.row + 1 < 598) {
    f.row += 1;
  }
  //remove food eaten by fish
  if(!foods.isEmpty()){
    for (Fish fish: fishes) {
      if(fishFoodCollision(fish, f)){
          foods.remove(f);
      }
    }  
    //now draw fish
    f.drawFood();
  }
}

public boolean fishFoodCollision(Fish fish, Food food) {
  return (food.row >= fish.row && food.row <= fish.row + fish.wt && 
    food.col >= fish.col && food.col <= fish.col + fish.ht);
}

public void updateFish(Fish f){
f.oldRow = f.row;
    f.oldCol = f.col;
        //calculate fish's path towards corrrect-typed food object
        if(!foods.isEmpty() && foods.get(activeFood).col != f.col && abs(foods.get(activeFood).row - f.row) > 1
          && foods.get(activeFood).active && (foods.get(activeFood).type == f.type || foods.get(activeFood).type == 1)) {
          int a = foods.get(activeFood).col - f.col;
          int b = foods.get(activeFood).row - f.row;
          double h = (int) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)); 
           
          int xMove = (int)(a / h * step);
          int yMove = (int)(b / h * step);
          
          f.col += xMove;
          f.row += yMove;
    }
    f.drawFish();
}

public void mousePressed(){
  //handle buttons
    if(clrBtn.isClicked()) {
      clrBtn.clr();
    } if(SFB.isClicked()) {
      SFB.scatterFish();
    } if(redFood.isClicked()) {
      redFood.foodColor(foodRed, 2);
    } if(greenFood.isClicked()) {
      greenFood.foodColor(foodGreen, 3);
    } if(whiteFood.isClicked()) {
      whiteFood.foodColor(whitet, 1);
    } if(blueFood.isClicked()) {
      blueFood.foodColor(foodBlue, 4);
    } if(RGBBtn.isClicked()) {
      RGBPalette();
    } if(neonBtn.isClicked()) {
      neonPalette();
    } if(onexSpd.isClicked()) {
      step = ps;
    } if(twoxSpd.isClicked()) {
      step = 2 * ps;
    } if(fourxSpd.isClicked()) {
      step = 4 * ps;
    } if(halfSpd.isClicked()) {
      step = ps / 2;
    }
    
    //spawn food
    else if(mouseX >= 0 && mouseX < 718){
     foods.get(foods.size() - 1).active = true;
     foods.get(foods.size() - 1).col = mouseX;
     foods.get(foods.size() - 1).row = mouseY;
     foods.add(new Food(0,0, activeFoodColor, false, activeFoodType));
   }
    
   //handle dragging
   for (int i = 0; i < fishes.length; i++) {
    if (!fishes[i].isOver())
      fishes[i].dontMove = true;
      fishes[i].offset_x = mouseX - fishes[i].col;
      fishes[i].offset_y = mouseY - fishes[i].row;
  }
}

public void mouseReleased() {
  //move the fish after dragging
  for (int i = 0; i < fishes.length; i++) {
    fishes[i].locked = false;
    fishes[i].dontMove = false;
  }
}
class Btn{ 
  int row;
  int col;
  int ht;
  int wt;
  int c1;
  int c2;
  String text;
  
  Btn(int col, int row, int wt, int ht, int c1, int c2, String text){
    this.col = col;
    this.row = row;
    this.wt = wt;
    this.ht = ht;
    this.c1 = c1;
    this.c2 = c2;
    this.text = text;
  }
  
  public void drawBtn(){
      fill(c1);
      rect(col,row,wt,ht);
      fill(c2);
      textSize(24);
      text(text,col + (ht / 2), row + 32);
  }
  
  public void scatterFish(){
    for(Fish f: fishes){
      f.row = (int) (random(598 - f.ht));
      f.col = (int) (random(720 - f.ht));
    }
  }
  
  public void clr() {
    foods.clear();
    foods.add(new Food(0, 0, whitet, false));
    initBG();
    clear = (clear + 1) % 2;
    
  }

  public boolean isClicked(){
    return(mouseX >= col && mouseX <= col + wt && mouseY >= row && 
    mouseY <= row + ht);
  }
  
  public void foodColor(int newColor, int type) {
    foods.get(foods.size() - 1).c = newColor;
    foods.get(foods.size() - 1).type = type;
    activeFoodColor = newColor;
    activeFoodType = type;
  }
}
  
class Fish {
  int col;
  int row;
  int oldRow;
  int oldCol;
  int rdel = 7;
  int cdel = 7;
  int ht = 6 * ps;
  int wt = 14 * ps;
  int x = col + (wt / 2);
  int y = row + (ht / 2);
  int c;
  int type;
  boolean locked;
  boolean dontMove;
  float offset_x, offset_y;
  
  int[][] trail = new int[180][150];
  
  Fish(int col, int row, int c, int type){
      this.col = col;
      this.row = row;
      this.c = c;
      oldCol = col;
      oldRow = row;
      this.type = type;
    }
    
  public void drawFish(){
    if (isClicked()) {
      locked = true;
    }
    if (locked && !dontMove) {
      
      col =  (int) (mouseX - offset_x);
      row =  (int) (mouseY - offset_y);
    }
    
    fill(c);
    //sprite for fish moving right
    if(col > oldCol && inBounds()) {
      for(int i = 0; i <= 0; i++) { rect(col + i * ps, row + 0 * ps, ps, ps);}
      for(int i = 6; i <= 11; i++) { rect(col + i * ps, row + 0 * ps, ps, ps);}
      for(int i = 0; i <= 1; i++) { rect(col + i * ps, row + 1 * ps, ps, ps);}
      for(int i = 5; i <= 12; i++) { rect(col + i * ps, row + 1 * ps, ps, ps);}
      for(int i = 0; i <= 2; i++) { rect(col + i * ps, row + 2 * ps, ps, ps);}
      for(int i = 4; i <= 13; i++) { rect(col + i * ps, row + 2 * ps, ps, ps);}
      for(int i = 0; i <= 13; i++) { rect(col + i * ps, row + 3 * ps, ps, ps);}
      for(int i = 4; i <= 13; i++) { rect(col + i * ps, row + 4 * ps, ps, ps);}
      for(int i = 0; i <= 2; i++) { rect(col + i * ps, row + 4 * ps, ps, ps);}
      for(int i = 0; i <= 1; i++) { rect(col + i * ps, row + 5 * ps, ps, ps);}
      for(int i = 5; i <= 12; i++) { rect(col + i * ps, row + 5 * ps, ps, ps);}
      for(int i = 0; i <= 0; i++) { rect(col + i * ps, row + 6 * ps, ps, ps);}
      for(int i = 6; i <= 11; i++) { rect(col + i * ps, row + 6 * ps, ps, ps);}
      fill(black);
      rect(col + 11 * ps, row + 2 * ps, ps, ps);
      //sprite for fish moving left
    } else if(inBounds()) {
      for(int i = 0; i <= 0; i++) { rect(col - i * ps, row + 0 * ps, ps, ps);}
      for(int i = 6; i <= 11; i++) { rect(col - i * ps, row + 0 * ps, ps, ps);}
      for(int i = 0; i <= 1; i++) { rect(col - i * ps, row + 1 * ps, ps, ps);}
      for(int i = 5; i <= 12; i++) { rect(col - i * ps, row + 1 * ps, ps, ps);}
      for(int i = 0; i <= 2; i++) { rect(col - i * ps, row + 2 * ps, ps, ps);}
      for(int i = 4; i <= 13; i++) { rect(col - i * ps, row + 2 * ps, ps, ps);}
      for(int i = 0; i <= 13; i++) { rect(col - i * ps, row + 3 * ps, ps, ps);}
      for(int i = 4; i <= 13; i++) { rect(col - i * ps, row + 4 * ps, ps, ps);}
      for(int i = 0; i <= 2; i++) { rect(col - i * ps, row + 4 * ps, ps, ps);}
      for(int i = 0; i <= 1; i++) { rect(col - i * ps, row + 5 * ps, ps, ps);}
      for(int i = 5; i <= 12; i++) { rect(col - i * ps, row + 5 * ps, ps, ps);}
      for(int i = 0; i <= 0; i++) { rect(col - i * ps, row + 6 * ps, ps, ps);}
      for(int i = 6; i <= 11; i++) { rect(col - i * ps, row + 6 * ps, ps, ps);}
      fill(black);
      rect(col - 11 * ps, row + 2 * ps, ps, ps);
    }
  }
  public void drawTrail(){
    if((oldCol / ps < 180) && (oldRow / ps < 150) && (oldCol / ps > 0) 
      && (oldRow / ps > 0)) {
      pv[oldCol / ps][oldRow / ps] = c;
    }
  }

  public boolean isOver() {
    float right_x = col + wt;
    float bottom_y = row + ht;
    return mouseX >= col && mouseX <= right_x &&
      mouseY >= row && mouseY <= bottom_y;
  }
  
  public boolean isClicked() {
    return isOver() && mousePressed && !dontMove;
  }
  
  public boolean inBounds() {
    return (col + wt >=0 && col + wt <= 720 && row + ht >= 0 && row + ht <= 600);
  }
}
class Food {
  int c; 
  int col;
  int row;
  boolean active;
  int x = col + (ps / 2);
  int y = row + (ps / 2);
  int type;
  
  Food(int col, int row, int c, boolean a){
    this.col = col;
    this.row = row;
    this.c = c;
    active = a;
    type = 1;
  }
  
  Food(int col, int row, int c, boolean a, int t){
    this.col = col;
    this.row = row;
    this.c = c;
    active = a;
    type = t;
  }
  
  public void drawFood(){
    if(active) {
      fill(c);
      rect(col, row, ps, ps, c);
    }
  }
    
}
public void drawBG() {
  //draw a rectangle that is the color of the corresponding color in the pixel array
   for (int i = 0; i < 180; i++) {
    for (int j = 0; j < 150; j++) {
      fill(pv[i][j]);
      rect(i*ps, j*ps, ps, ps);
    }
  }
}

public void initBG() {
  //fill pixel array with black then draw background objects and buttons
  for (int i = 0; i < 180; i++) {
    for (int j = 0; j < 150; j++) {
      pv[i][j] = black;
    }
  }
  drawDarkBlueCoral(32, 41);
  drawDarkBlueCoral(128,43);
  drawPinkCoral1(0, 43);
  drawPurpleCoral(43);
  drawPinkCoral1(149, 43);
  drawDarkBlueCoral(0, 43);
  for (Btn b: buttons){
    b.drawBtn();
  }
}
public void RGBPalette(){
  pink1 = color(255,0,0);
  gblue = dblue = color(0,0,255);
  mblue = color(0,255,0);
  lblue = white;
  lp = white;
  dp = red;
  f1.c = fishRed;
  f2.c = fishBlue;
  f3.c = fishGreen;
  
  foodGreen = fishGreen;
  foodBlue = lbluet;
  foodRed = fishRed; 
  
  redFood.c1 = red;
  greenFood.c1 = green;
  blueFood.c1 = blue;
  initBG();
}

public void neonPalette(){
  //pinkCoral
    pink1 = color(183,41,239, 98);
//dark blue coral colors
    lblue = color(100,150,254, 98);
    mblue = color(34,101,226, 98);
    dblue = color(52,50,90, 98);
    gblue = color(29,91,153, 98);
    lp = color(132, 57, 237);
    dp = color(107,52,170);
    
   foodGreen = color(132, 57, 237, 98);
   foodBlue = lbluet;
   foodRed = pink1;
   
   redFood.c1 = color(183,41,239);
   blueFood.c1 = color(100,150,254);
   greenFood.c1 = color(132, 57, 237);
   f1.c = pink1;
   f2.c = lblue;
   f3.c = lp;
  initBG();
}
//very long blue coral sprite
public void drawDarkBlueCoral(int y, int m){
  int ax = 23 + y;
  for (int k = 21 + y; k <= 23 + y; k++){ pv[k][86 + m] = gblue; }
  
  pv[20 + y][87 + m] = pv[23 + y][87 + m] = gblue;
  pv[21 + y][87 + m] = mblue;
  for (int k = 25 + y; k <= 27 + y; k++){ pv[k][87 + m] = gblue; }
  
  pv[18 + y][88 + m] = pv[19 + y][88 + m] = gblue;
  pv[20 + y][88 + m] = pv[21 + y][88 + m] = mblue;
  for (int k = 22 + y; k <= 26 + y; k++){ pv[k][88 + m] = gblue; }
  pv[28 + y][88 + m] = pv[29 + y][88 + m] = gblue;
  
  for (int k = 16 + y; k <= 18 + y; k++){ pv[k][89 + m] = gblue; }
  for (int k = 19 + y; k <= 21 + y; k++){ pv[k][89 + m] = mblue; }
  for (int k = 22 + y; k <= 24 + y; k++){ pv[k][89 + m] = gblue; }
  pv[27 + y][89 + m] = pv[29 + y][89 + m] = pv[30 + y][89 + m] = gblue;
  pv[25 + y][89 + m] = pv[26 + y][89 + m] = dblue;
  
  pv[16 + y][90 + m] = gblue;
  pv[18 + y][90 + m] = mblue;
  pv[19 + y][90 + m] = pv[20 + y][90 + m] = pv[27 + y][90 + m] = dblue;
  for (int k = 22 + y; k <= 24 + y; k++){ pv[k][90 + m] = gblue; }
  for (int k = 28 + y; k <= 30 + y; k++){ pv[k][90 + m] = gblue; }
  
  for (int k = 15 + y; k <= 18 + y; k++){ pv[k][91 + m] = gblue; }
  pv[19 + y][91 + m] = dblue;
  pv[20 + y][91 + m] = mblue;
  pv[21 + y][91 + m] = gblue;
  pv[26 + y][91 + m] = pv[27 + y][91 + m] = dblue;
  for (int k = 23 + y; k <= 25 + y; k++){ pv[k][91 + m] = gblue; }
  for (int k = 30 + y; k <= 31 + y; k++){ pv[k][91 + m] = gblue; }
  
  pv[16 + y][92 + m] = pv[23 + y][92 + m] = pv[25 + y][92 + m] = pv[27 + y][92 + m] = pv[28 + y][92 + m] = pv[31 + y][92 + m] = gblue;
  pv[24 + y][92 + m] = mblue;
  pv[26 + y][92 + m] = dblue;
  for (int k = 17 + y; k <= 20 + y; k++){ pv[k][92 + m] = mblue; }
  
  pv[11 + y][93 + m] = lblue;
  pv[12 + y][93 + m] = pv[14 + y][93 + m] = pv[17 + y][93 + m] = pv[28 + y][93 + m] = pv[30 + y][93 + m] = pv[31 + y][93 + m] = gblue;
  pv[23 + y][93 + m] = mblue;
  pv[13 + y][93 + m] = pv[15 + y][93 + m] = pv[24 + y][93 + m] = pv[27 + y][93 + m] = dblue;
  
  pv[27 + y][94 + m] = dblue;
  pv[11 + y][94 + m] = pv[13 + y][94 + m] = pv[16 + y][94 + m] = pv[29 + y][94 + m] = pv[31 + y][94 + m] = gblue;
  pv[20 + y][94 + m] = pv[23 + y][94 + m] = pv[28 + y][94 + m] = pv[32 + y][94 + m] = mblue;
  pv[22 + y][94 + m] = lblue;
  
  pv[12 + y][95 + m] = pv[13 + y][95 + m] = pv[20 + y][95 + m] = pv[25 + y][95 + m] = pv[27 + y][95 + m] = pv[28 + y][95 + m] = 
    pv[30 + y][95 + m] = pv[31 + y][95 + m] = gblue;
  pv[24 + y][95 + m] = pv[26 + y][95 + m] = dblue;
  pv[14 + y][95 + m] = pv[23 + y][95 + m] = pv[32 + y][95 + m] = pv[33 + y][95 + m] = pv[34 + y][95 + m] = mblue;
  pv[22 + y][95 + m] = lblue;
  
  pv[9 + y][96 + m] = pv[11 + y][96 + m] = pv[13 + y][96 + m] = pv[21 + y][96 + m] = pv[25 + y][96 + m] = pv[26 + y][96 + m] = 
    pv[27 + y][96 + m] = pv[31 + y][96 + m] = gblue;
  pv[14 + y][96 + m] = pv[23 + y][96 + m] = pv[24 + y][96 + m] = pv[33 + y][96 + m] = pv[34 + y][96 + m] = mblue;
  pv[22 + y][96 + m] = pv[29 + y][96 + m] = lblue;
  
  pv[9 + y][97 + m] = pv[10 + y][97 + m] = pv[18 + y][97 + m] = pv[19 + y][97 + m] =  pv[31 + y][97 + m] = pv[35 + y][97 + m] = 
    pv[36 + y][97 + m] = gblue;
  pv[15 + y][97 + m] = dblue;
  pv[14 + y][97 + m] = pv[16 + y][97 + m] = pv[24 + y][97 + m] = pv[29 + y][97 + m] = pv[30 + y][97 + m] = pv[33 + y][97 + m] = mblue;
  pv[22 + y][97 + m] = pv[23 + y][97 + m] = lblue;
  
  pv[19 + y][98 + m] = pv[20 + y][98 + m] = pv[21 + y][98 + m] = pv[24 + y][98 + m] = pv[25 + y][98 + m] = pv[28 + y][98 + m] = 
    pv[29 + y][98 + m] = pv[31 + y][98 + m] = pv[36 + y][98 + m] = pv[37 + y][98 + m] = pv[39 + y][98 + m] = pv[40 + y][98 + m] = gblue;
  pv[15 + y][98 + m] = pv[38 + y][98 + m] = dblue;
  pv[14 + y][98 + m] = pv[16 + y][98 + m] = pv[17 + y][98 + m] = pv[18 + y][98 + m] = pv[30 + y][98 + m] = pv[33 + y][98 + m] = 
    pv[34 + y][98 + m] = pv[35 + y][98 + m] = mblue;
  pv[22 + y][98 + m] = pv[23 + y][98 + m] = lblue;
  
  pv[19 + y][99 + m] = pv[20 + y][99 + m] = pv[25 + y][99 + m] = pv[28 + y][99 + m] = pv[30 + y][99 + m] = pv[32 + y][99 + m] = 
    pv[33 + y][99 + m] = pv[34 + y][99 + m] = pv[37 + y][99 + m] = pv[41 + y][99 + m] = gblue;
  pv[38 + y][99 + m] = pv[39 + y][99 + m] = pv[40 + y][99 + m] = dblue;
  pv[11 + y][99 + m] = pv[21 + y][99 + m] = pv[22 + y][99 + m] = pv[24 + y][99 + m] = pv[29 + y][99 + m] = mblue;
  for (int k = 14 + y; k <= 17 + y; k++){ pv[k][99 + m] = gblue; }
  pv[23 + y][99 + m] = pv[26 + y][99 + m] = pv[32 + y][99 + m] = lblue;
  
  pv[18 + y][100 + m] = pv[19 + y][100 + m] = pv[25 + y][100 + m] = pv[28 + y][100 + m] = pv[31 + y][100 + m] = pv[32 + y][100 + m] = 
    pv[38 + y][100 + m] = pv[39 + y][100 + m] = pv[41 + y][100 + m] = gblue;
  pv[11 + y][100 + m] = pv[14 + y][100 + m] = pv[15 + y][100 + m] = pv[16 + y][100 + m] = pv[24 + y][100 + m] = pv[35 + y][100 + m] = 
    pv[36 + y][100 + m] = pv[37 + y][100 + m] = pv[40 + y][100 + m] = dblue;
  pv[12 + y][100 + m] = pv[13 + y][100 + m] = pv[17 + y][100 + m] = pv[21 + y][100 + m] = mblue;
  pv[20 + y][100 + m] = pv[22 + y][100 + m] = pv[23 + y][100 + m] = pv[26 + y][100 + m] = lblue;
  
  pv[17 + y][101 + m] = pv[25 + y][101 + m] = pv[26 + y][101 + m] = pv[31 + y][101 + m] = pv[32 + y][101 + m] = pv[36 + y][101 + m] = 
    pv[37 + y][101 + m] = pv[42 + y][101 + m] = gblue;
  pv[10 + y][101 + m] = pv[12 + y][101 + m] = pv[14 + y][101 + m] = pv[19 + y][101 + m] = pv[21 + y][101 + m] = pv[27 + y][101 + m] = 
    pv[28 + y][101 + m] = pv[43 + y][101 + m] = dblue;  
  for (int k = 38 + y; k <= 41 + y; k++){ pv[k][101 + m] = dblue; }
  pv[13 + y][101 + m] = pv[16 + y][101 + m] = pv[20 + y][101 + m] = pv[23 + y][101 + m] = pv[24 + y][101 + m] = pv[24 + y][101 + m] = 
    pv[35 + y][101 + m] = mblue;  
  pv[18 + y][101 + m] = pv[22 + y][101 + m] = lblue;  
    
  pv[6 + y][102 + m] = pv[7 + y][102 + m] = pv[8 + y][102 + m] = pv[10 + y][102 + m] = pv[25 + y][102 + m] = pv[27 + y][102 + m] = 
    pv[30 + y][102 + m] = pv[31 + y][102 + m] = pv[32 + y][102 + m] = pv[39 + y][102 + m] = pv[40 + y][102 + m] = 
    pv[43 + y][102 + m] = gblue;
  pv[9 + y][102 + m] = pv[12 + y][102 + m] = pv[36 + y][102 + m] = pv[37 + y][102 + m] = pv[38 + y][102 + m] = pv[41 + y][102 + m] = 
    pv[42 + y][102 + m] = dblue;
  pv[11 + y][102 + m] = pv[19 + y][102 + m] = pv[20 + y][102 + m] = pv[22 + y][102 + m] = pv[24 + y][102 + m] = pv[33 + y][102 + m] = 
    pv[34 + y][102 + m] = pv[35 + y][102 + m] = mblue;
    pv[18 + y][102 + m] = pv[23 + y][102 + m] = pv[26 + y][102 + m] = pv[28 + y][102 + m] = lblue;
    
  pv[6 + y][103 + m] = pv[7 + y][103 + m] = pv[26 + y][103 + m] = pv[27 + y][103 + m] = pv[28 + y][103 + m] = pv[30 + y][103 + m] = 
    pv[35 + y][103 + m] = pv[39 + y][103 + m] = pv[42 + y][103 + m] = gblue;
  pv[9 + y][103 + m] = pv[10 + y][103 + m] = pv[11 + y][103 + m] = pv[14 + y][103 + m] = pv[25 + y][103 + m] = pv[38 + y][103 + m] = 
    pv[40 + y][103 + m] = pv[43 + y][103 + m] = dblue;
  pv[12 + y][103 + m] = pv[15 + y][103 + m] = pv[16 + y][103 + m] = pv[17 + y][103 + m] = pv[19 + y][103 + m] = pv[22 + y][103 + m] = 
    pv[24 + y][103 + m] = pv[33 + y][103 + m] = pv[34 + y][103 + m] = mblue;
  pv[13 + y][103 + m] = pv[18 + y][103 + m] = pv[23 + y][103 + m] = lblue;
    
  pv[6 + y][104 + m] = pv[20 + y][104 + m] =  pv[25 + y][104 + m] =  pv[27 + y][104 + m] =  pv[28 + y][104 + m] =  pv[30 + y][104 + m] 
    =  pv[31 + y][104 + m] =  pv[32 + y][104 + m] =  pv[35 + y][104 + m] = pv[43 + y][104 + m] =  gblue;  
  for (int k = 37 + y; k <= 40 + y; k++){ pv[k][104 + m] = gblue; }
  pv[21 + y][104 + m] =  pv[26 + y][104 + m] =  pv[36 + y][104 + m] =  pv[41 + y][104 + m] =  pv[42 + y][104 + m] =  dblue;  
  for (int k = 14 + y; k <= 18 + y; k++){ pv[k][104 + m] = dblue; }
  pv[13 + y][104 + m] =  pv[14 + y][104 + m] =  pv[22 + y][104 + m] =  pv[24 + y][104 + m] =  pv[33 + y][104 + m] =  
    pv[34 + y][104 + m] =  mblue;  
  pv[12 + y][104 + m] =  pv[19 + y][104 + m] =  pv[23 + y][104 + m] =  lblue;  
    
  pv[21 + y][105 + m] = pv[25 + y][105 + m] = pv[28 + y][105 + m] = pv[43 + y][105 + m] = pv[44 + y][105 + m] = gblue;
  for (int k = 27 + y; k <= 31 + y; k++){ pv[k][105 + m] = gblue; }
  for (int k = 33 + y; k <= 36 + y; k++){ pv[k][105 + m] = gblue; }
  pv[11 + y][105 + m] = pv[14 + y][105 + m] = pv[17 + y][105 + m] = pv[19 + y][105 + m] = pv[37 + y][105 + m] = dblue;
  pv[16 + y][105 + m] = pv[18 + y][105 + m] = pv[22 + y][105 + m] = pv[24 + y][105 + m] = pv[26 + y][105 + m] = pv[39 + y][105 + m] 
    = pv[42 + y][105 + m] = mblue;
  pv[15 + y][105 + m] = pv[20 + y][105 + m] = pv[23 + y][105 + m] = pv[40 + y][105 + m] = lblue;
  
  pv[1 + y][106 + m] = pv[2 + y][106 + m] = pv[13 + y][106 + m] = pv[27 + y][106 + m] = pv[34 + y][106 + m] = pv[35 + y][106 + m] = 
    pv[39 + y][106 + m] = pv[44 + y][106 + m] = pv[47 + y][106 + m] = pv[48 + y][106 + m] = pv[51 + y][106 + m] = gblue;
  pv[11 + y][106 + m] = pv[12 + y][106 + m] = pv[14 + y][106 + m] = pv[16 + y][106 + m] = pv[17 + y][106 + m] = pv[19 + y][106 + m] = pv[25 + y][106 + m] 
    = pv[36 + y][106 + m] = pv[43 + y][106 + m] = dblue;
  pv[15 + y][106 + m] = pv[18 + y][106 + m] = pv[24 + y][106 + m] = pv[38 + y][106 + m] = mblue;
  for (int k = 28 + y; k <= 33 + y; k++){ pv[k][106 + m] = mblue; }
  pv[26 + y][106 + m] = lblue;
  for (int k = 20 + y; k <= 23 + y; k++){ pv[k][106 + m] = lblue; }
  
if(m < 43) {  
  pv[26 + y][107 + m] = pv[27 + y][107 + m] = pv[28 + y][107 + m] = pv[29 + y][107 + m] = pv[31 + y][107 + m] = 
    pv[38 + y][107 + m] = pv[39 + y][107 + m] = pv[40 + y][107 + m] = pv[44 + y][107 + m] = pv[47 + y][107 + m] = 
    pv[48 + y][107 + m] = pv[49 + y][107 + m] = pv[50 + y][107 + m] = pv[51 + y][107 + m] = pv[52 + y][107 + m] = 
    pv[53 + y][107 + m] = gblue;
  pv[12 + y][107 + m] = pv[14 + y][107 + m] = pv[43 + y][107 + m] = dblue;
  pv[10 + y][107 + m] = pv[11 + y][107 + m] = pv[13 + y][107 + m] = pv[17 + y][107 + m] = pv[19 + y][107 + m] = 
    pv[32 + y][107 + m] = pv[33 + y][107 + m] = pv[34 + y][107 + m] = pv[35 + y][107 + m] = pv[36 + y][107 + m] = mblue;
  pv[21 + y][107 + m] = pv[22 + y][107 + m] = pv[23 + y][107 + m] = pv[37 + y][107 + m] = lblue;
  
  pv[0 + y][108 + m] = pv[1 + y][108 + m] = pv[12 + y][107 + m] = pv[14 + y][107 + m] = pv[17 + y][107 + m] = 
    pv[27 + y][108 + m] = pv[37 + y][108 + m] = pv[40 + y][108 + m] = pv[44 + y][108 + m] = pv[46 + y][108 + m] = 
    pv[52 + y][108 + m] = pv[54 + y][108 + m] = pv[43 + y][108 + m] = gblue;
  pv[2 + y][108 + m] = pv[3 + y][108 + m] = pv[11 + y][108 + m] = pv[18 + y][108 + m] = pv[42 + y][108 + m] = 
    pv[47 + y][108 + m] = pv[48 + y][108 + m] = pv[49 + y][108 + m] = pv[50 + y][108 + m] = pv[51 + y][108 + m] = 
    pv[53 + y][108 + m] = dblue;
  pv[10 + y][108 + m] = pv[11 + y][108 + m] = pv[13 + y][108 + m] = pv[17 + y][108 + m] = pv[19 + y][108 + m] = 
    pv[32 + y][108 + m] = pv[33 + y][108 + m] = pv[34 + y][108 + m] = pv[35 + y][108 + m] = pv[36 + y][108 + m] = mblue;
  pv[21 + y][108 + m] = pv[22 + y][108 + m] = pv[23 + y][108 + m] = pv[37 + y][108 + m] = lblue;
}  
  
}
//coral sprite
//slightly taller than necessary
public void drawPinkCoral1(int y, int m){
  pv[5 + y][81 + m] = pink1;
  pv[4 + y][82 + m] = pv[5 + y][82 + m] = pv[6 + y][82 + m] = pink1;
  pv[4 + y][83 + m] = pv[5 + y][83 + m] = pv[6 + y][83 + m] = pink1;
  pv[2 + y][84 + m] = pv[4 + y][84 + m] = pv[5 + y][84 + m] = pv[6 + y][84 + m] = pink1;
  pv[2 + y][85 + m] = pv[3 + y][85 + m] = pv[4 + y][85 + m] = pv[5 + y][85 + m] 
    = pv[6 + y][85 + m] = pink1;
  pv[2 + y][86 + m] = pv[3 + y][86 + m] = pv[4 + y][86 + m] = pv[5 + y][86 + m] = pink1;
  pv[3 + y][87 + m] = pv[4 + y][87 + m] = pv[6 + y][87 + m] = pv[7 + y][87 + m] 
    = pv[8 + y][86 + m] = pink1;
  pv[3 + y][88 + m] = pv[4 + y][88 + m] = pv[5 + y][88 + m] = pv[6 + y][88 + m] 
    = pv[7 + y][88 + m] = pv[8 + y][88 + m] = pink1;
  pv[0 + y][89 + m] = pv[1 + y][89 + m] = pv[2 + y][89 + m] = pv[3 + y][89 + m] 
    = pv[4 + y][89 + m] = pv[5 + y][89 + m] = pv[6 + y][89 + m] 
    = pv[7 + y][89 + m] = pv[8 + y][89 + m] = pv[9 + y][89 + m] = pink1;
  pv[0 + y][90 + m] = pv[1 + y][90 + m] = pv[2 + y][90 + m] = pv[4 + y][90 + m] = pink1;
  pv[4 + y][91 + m] = pink1;
  pv[4 + y][92 + m] = pv[8 + y][92 + m] = pink1;
  pv[0 + y][93 + m] = pv[1 + y][93 + m] = pv[2 + y][93 + m] = pv[4 + y][93 + m] 
    = pv[6 + y][93 + m] = pv[7 + y][93 + m] = pv[8 + y][93 + m] = pv[9 + y][93 + m] = pink1;
  for (int k = (0 + y); k <= (9 + y); k++){
    pv[k][94 + m] = pink1;
  }
  pv[0 + y][95 + m] = pv[1 + y][95 + m] = pv[2 + y][95 + m] = pv[4 + y][95 + m] 
    = pv[5 + y][95 + m] = pv[6 + y][95 + m] = pv[7 + y][95 + m] = pv[8 + y][95 + m] = pink1;
  pv[4 + y][96 + m] = pink1;
  pv[4 + y][97 + m] = pink1;
  pv[0 + y][98 + m] = pv[1 + y][98 + m] = pv[4 + y][98 + m] = pv[5 + y][98 + m] = 
    pv[6 + y][98 + m] = pv[7 + y][98 + m] = pv[8 + y][98 + m] = pv[9 + y][98 + m] = pink1;
  for (int k = 0 + y; k <= 10 + y; k++){
    pv[k][99 + m] = pink1;
  }
  pv[0 + y][100 + m] = pv[1 + y][100 + m] = pv[4 + y][100 + m] = pv[5 + y][100 + m] = 
    pv[6 + y][100 + m] = pv[7 + y][100 + m] = pv[8 + y][100 + m] = pv[9 + y][100 + m] = 
    pv[10 + y][100 + m] = pink1;
  pv[0 + y][101 + m] = pv[4 + y][101 + m] = pv[7 + y][101 + m] = pv[8 + y][101 + m] = 
    pv[9 + y][101 + m] = pink1;
  pv[4 + y][102 + m] = pv[5 + y][102 + m] = pink1;
  pv[2 + y][103 + m] = pv[3 + y][103 + m] = pv[4 + y][103 + m] = pv[5 + y][103 + m] = pink1;
  for (int k = 0 + y; k <= 5 + y; k++){
    pv[k][104 + m] = pink1;
  }
  pv[9 + y][104 + m] = pv[10 + y][104 + m] = pink1;
  for (int k = 0 + y; k <= 10 + y; k++){ pv[k][105 + m] = pink1; }
  pv[0 + y][105 + m] = pink1;
  for (int k = 3 + y; k <= 10 + y; k++){
    pv[k][105 + m] = pink1;
  }
  for (int k = 0 + y; k <= 5 + y; k++){ pv[k][106 + m] = pink1; }
  //for (int k = 4 + y; k <= 10 + y; k++){ pv[k][108 + m] = pink1; }
  //pv[4 + y][109 + m] = pv[5 + y][109 + m] = pv[7 + y][109 + m] = pv[8 + y][109 + m] = 
  //  pv[9 + y][109 + m] = pink1;
  //for (int k = 0 + y; k <= 5 + y; k++){ pv[k][110 + m] = pink1; }
  //pv[0 + y][111 + m] = pv[4 + y][111 + m] = pv[5 + y][111 + m] =  pink1;
  //for (int k = 8 + y; k <= 12 + y; k++){ pv[k][111 + m] = pink1; }
  //for (int k = 0 + y; k <= 1 + y; k++){ pv[k][112 + m] = pink1; }
  //for (int k = 3 + y; k <= 5 + y; k++){ pv[k][112 + m] = pink1; }
  //for (int k = 7 + y; k <= 12 + y; k++){ pv[k][112 + m] = pink1; }
  //for (int k = 0 + y; k <= 11 + y; k++){ pv[k][113 + m] = pink1; }
  //for (int k = 0 + y; k <= 5 + y; k++){ pv[k][114 + m] = pink1; }
  //pv[8 + y][114 + m] = pink1;
  //pv[8 + y][115 + m] = pink1;
  //for (int k = 1 + y; k <= 5 + y; k++){ pv[k][115 + m] = pink1; }
  //for (int k = 0 + y; k <= 6 + y; k++){ pv[k][116 + m] = pink1; }
  //pv[8 + y][116 + m] = pv[9 + y][116 + m] = pink1;
  //for (int k = 0 + y; k <= 11 + y; k++){ pv[k][117 + m] = pink1; }
  //pv[1 + y][118 + m] = pink1;
  //for (int k = 5 + y; k <= 11 + y; k++){ pv[k][118 + m] = pink1; }
  //pv[0 + y][119 + m] = pv[1 + y][119 + m] = pink1;
  //for (int k = 3 + y; k <= 10 + y; k++){ pv[k][119 + m] = pink1; }
  //for (int k = 0 + y; k <= 8 + y; k++){ pv[k][120 + m] = pink1; }
  //pv[10 + y][120 + m] = pink1;
  //pv[1 + y][121 + m] = pv[2 + y][121 + m] = pink1;
  //for (int k = 5 + y; k <= 11 + y; k++){ pv[k][121 + m] = pink1; }
  //for (int k = 2 + y; k <= 9 + y; k++){ pv[k][122 + m] = pink1; }
  //pv[11 + y][122 + m] = pink1;
  //for (int k = 3 + y; k <= 6 + y; k++){ pv[k][123 + m] = pink1; }
  //for (int k = 5 + y; k <= 6 + y; k++){ pv[k][124 + m] = pink1; }
  //for (int k = 5 + y; k <= 6 + y; k++){ pv[k][125 + m] = pink1; }
  //for (int k = 8 + y; k <= 9 + y; k++){ pv[k][125 + m] = pink1; }
}
public void drawPurpleCoral(int m){
  for (int k = 134; k <= 136; k++){ pv[k][70 + m] = lp; }
  
  for (int k = 134; k <= 137; k++){ pv[k][71 + m] = lp; }
  
  for (int k = 134; k <= 137; k++){ pv[k][72 + m] = lp; }
  
  for (int k = 124; k <= 126; k++){ pv[k][73 + m] = lp; }
  for (int k = 134; k <= 137; k++){ pv[k][73 + m] = lp; }
  
  for (int k = 124; k <= 127; k++){ pv[k][74 + m] = lp; }
  for (int k = 135; k <= 138; k++){ pv[k][74 + m] = lp; }
  
  for (int k = 115; k <= 117; k++){ pv[k][75 + m] = lp; }
  for (int k = 125; k <= 127; k++){ pv[k][75 + m] = lp; }
  for (int k = 135; k <= 138; k++){ pv[k][75 + m] = lp; }
  
  for (int k = 115; k <= 118; k++){ pv[k][76 + m] = lp; }
  for (int k = 125; k <= 127; k++){ pv[k][76 + m] = lp; }
  for (int k = 135; k <= 139; k++){ pv[k][76 + m] = lp; }
  for (int k = 145; k <= 146; k++){ pv[k][76 + m] = lp; }
  
  for (int k = 116; k <= 118; k++){ pv[k][77 + m] = lp; }
  for (int k = 125; k <= 128; k++){ pv[k][77 + m] = lp; }
  for (int k = 136; k <= 139; k++){ pv[k][77 + m] = lp; }
  for (int k = 144; k <= 147; k++){ pv[k][77 + m] = lp; }
  
  for (int k = 116; k <= 118; k++){ pv[k][78 + m] = lp; }
  for (int k = 125; k <= 128; k++){ pv[k][78 + m] = lp; }
  for (int k = 136; k <= 140; k++){ pv[k][78 + m] = lp; }
  for (int k = 144; k <= 146; k++){ pv[k][78 + m] = lp; }
  for (int k = 136; k <= 137; k++){ pv[k][78 + m] = dp; }
  for (int k = 147; k <= 147; k++){ pv[k][78 + m] = dp; }
  
  for (int k = 116; k <= 119; k++){ pv[k][79 + m] = lp; }
  for (int k = 126; k <= 129; k++){ pv[k][79 + m] = lp; }
  for (int k = 137; k <= 140; k++){ pv[k][79 + m] = lp; }
  for (int k = 144; k <= 145; k++){ pv[k][79 + m] = lp; }
  for (int k = 137; k <= 137; k++){ pv[k][79 + m] = dp; }
  for (int k = 146; k <= 147; k++){ pv[k][79 + m] = dp; }
  
  for (int k = 117; k <= 119; k++){ pv[k][80 + m] = lp; }
  for (int k = 126; k <= 129; k++){ pv[k][80 + m] = lp; }
  for (int k = 137; k <= 140; k++){ pv[k][80 + m] = lp; }
  for (int k = 144; k <= 145; k++){ pv[k][80 + m] = lp; }
  for (int k = 146; k <= 147; k++){ pv[k][80 + m] = dp; }
  
  for (int k = 108; k <= 108; k++){ pv[k][81 + m] = lp; }
  for (int k = 117; k <= 120; k++){ pv[k][81 + m] = lp; }
  for (int k = 126; k <= 130; k++){ pv[k][81 + m] = lp; }
  for (int k = 137; k <= 140; k++){ pv[k][81 + m] = lp; }
  for (int k = 145; k <= 145; k++){ pv[k][81 + m] = lp; }
  for (int k = 146; k <= 147; k++){ pv[k][81 + m] = dp; }
  
  for (int k = 106; k <= 109; k++){ pv[k][82 + m] = lp; }
  for (int k = 117; k <= 120; k++){ pv[k][82 + m] = lp; }
  for (int k = 127; k <= 130; k++){ pv[k][82 + m] = lp; }
  for (int k = 137; k <= 140; k++){ pv[k][82 + m] = lp; }
  for (int k = 144; k <= 147; k++){ pv[k][82 + m] = dp; }
  
  for (int k = 105; k <= 110; k++){ pv[k][83 + m] = lp; }
  for (int k = 118; k <= 120; k++){ pv[k][83 + m] = lp; }
  for (int k = 127; k <= 131; k++){ pv[k][83 + m] = lp; }
  for (int k = 139; k <= 141; k++){ pv[k][83 + m] = lp; }
  for (int k = 145; k <= 147; k++){ pv[k][83 + m] = dp; }
  for (int k = 138; k <= 138; k++){ pv[k][83 + m] = dp; }
  
  for (int k = 105; k <= 110; k++){ pv[k][84 + m] = lp; }
  for (int k = 118; k <= 121; k++){ pv[k][84 + m] = lp; }
  for (int k = 128; k <= 131; k++){ pv[k][84 + m] = lp; }
  for (int k = 140; k <= 141; k++){ pv[k][84 + m] = lp; }
  for (int k = 145; k <= 147; k++){ pv[k][84 + m] = dp; }
  for (int k = 138; k <= 139; k++){ pv[k][84 + m] = dp; }

  for (int k = 106; k <= 110; k++){ pv[k][85 + m] = lp; }
  for (int k = 119; k <= 121; k++){ pv[k][85 + m] = lp; }
  for (int k = 128; k <= 131; k++){ pv[k][85 + m] = lp; }
  for (int k = 140; k <= 141; k++){ pv[k][85 + m] = lp; }
  for (int k = 138; k <= 139; k++){ pv[k][85 + m] = dp; }
  
  for (int k = 99; k <= 100; k++){ pv[k][86 + m] = lp; }
  for (int k = 119; k <= 121; k++){ pv[k][86 + m] = lp; }
  for (int k = 107; k <= 110; k++){ pv[k][86 + m] = lp; }
  for (int k = 128; k <= 130; k++){ pv[k][86 + m] = lp; }
  for (int k = 132; k <= 133; k++){ pv[k][86 + m] = lp; }
  for (int k = 138; k <= 141; k++){ pv[k][86 + m] = dp; }
  for (int k = 131; k <= 131; k++){ pv[k][86 + m] = dp; }
  
  for (int k = 99; k <= 101; k++){ pv[k][87 + m] = lp; }
  for (int k = 107; k <= 110; k++){ pv[k][87 + m] = lp; }
  for (int k = 119; k <= 122; k++){ pv[k][87 + m] = lp; }
  for (int k = 129; k <= 129; k++){ pv[k][87 + m] = lp; }
  for (int k = 138; k <= 141; k++){ pv[k][87 + m] = dp; }
  for (int k = 130; k <= 133; k++){ pv[k][87 + m] = dp; }
  
  for (int k = 98; k <= 101; k++){ pv[k][88 + m] = lp; }
  for (int k = 107; k <= 110; k++){ pv[k][88 + m] = lp; }
  for (int k = 114; k <= 115; k++){ pv[k][88 + m] = lp; }
  for (int k = 119; k <= 122; k++){ pv[k][88 + m] = lp; }
  for (int k = 138; k <= 142; k++){ pv[k][88 + m] = dp; }
  for (int k = 130; k <= 133; k++){ pv[k][88 + m] = dp; }
  
  for (int k = 99; k <= 102; k++){ pv[k][89 + m] = lp; }
  for (int k = 120; k <= 122; k++){ pv[k][89 + m] = lp; }
  for (int k = 113; k <= 115; k++){ pv[k][89 + m] = lp; }
  for (int k = 107; k <= 110; k++){ pv[k][89 + m] = lp; }
  for (int k = 138; k <= 142; k++){ pv[k][89 + m] = dp; }
  for (int k = 130; k <= 133; k++){ pv[k][89 + m] = dp; }
  
  for (int k = 99; k <= 103; k++){ pv[k][90 + m] = lp; }
  for (int k = 120; k <= 122; k++){ pv[k][90 + m] = lp; }
  for (int k = 108; k <= 111; k++){ pv[k][90 + m] = lp; }
  for (int k = 113; k <= 116; k++){ pv[k][90 + m] = lp; }
  for (int k = 130; k <= 134; k++){ pv[k][90 + m] = dp; }
  for (int k = 124; k <= 125; k++){ pv[k][90 + m] = dp; }
  
  for (int k = 93; k <= 94; k++){ pv[k][91 + m] = lp; }
  for (int k = 100; k <= 103; k++){ pv[k][91 + m] = lp; }
  for (int k = 108; k <= 111; k++){ pv[k][91 + m] = lp; }
  for (int k = 113; k <= 116; k++){ pv[k][91 + m] = lp; }
  for (int k = 120; k <= 121; k++){ pv[k][91 + m] = lp; }
  for (int k = 130; k <= 134; k++){ pv[k][91 + m] = dp; }
  for (int k = 124; k <= 126; k++){ pv[k][91 + m] = dp; }
  
  for (int k = 92; k <= 94; k++){ pv[k][92 + m] = lp; }
  for (int k = 100; k <= 104; k++){ pv[k][92 + m] = lp; }
  for (int k = 109; k <= 116; k++){ pv[k][92 + m] = lp; }
  for (int k = 120; k <= 120; k++){ pv[k][92 + m] = lp; }
  for (int k = 130; k <= 134; k++){ pv[k][92 + m] = dp; }
  for (int k = 121; k <= 126; k++){ pv[k][92 + m] = dp; }
 
  for (int k = 101; k <= 105; k++){ pv[k][93 + m] = lp; }
  for (int k = 92; k <= 94; k++){ pv[k][93 + m] = lp; }
  for (int k = 109; k <= 115; k++){ pv[k][93 + m] = lp; }
  for (int k = 130; k <= 134; k++){ pv[k][93 + m] = dp; }
  for (int k = 120; k <= 126; k++){ pv[k][93 + m] = dp; }
  
  for (int k = 92; k <= 95; k++){ pv[k][94 + m] = lp; }
  for (int k = 102; k <= 105; k++){ pv[k][94 + m] = lp; }
  for (int k = 108; k <= 115; k++){ pv[k][94 + m] = lp; }
  for (int k = 130; k <= 134; k++){ pv[k][94 + m] = dp; }
  for (int k = 120; k <= 124; k++){ pv[k][94 + m] = dp; }
  
  for (int k = 92; k <= 96; k++){ pv[k][95 + m] = lp; }
  for (int k = 102; k <= 106; k++){ pv[k][95 + m] = lp; }
  for (int k = 108; k <= 114; k++){ pv[k][95 + m] = lp; }
  for (int k = 130; k <= 134; k++){ pv[k][95 + m] = dp; }
  for (int k = 120; k <= 124; k++){ pv[k][95 + m] = dp; }
  
  for (int k = 92; k <= 96; k++){ pv[k][96 + m] = lp; }
  for (int k = 103; k <= 107; k++){ pv[k][96 + m] = lp; }
  for (int k = 108; k <= 113; k++){ pv[k][96 + m] = lp; }
  for (int k = 130; k <= 133; k++){ pv[k][96 + m] = dp; }
  for (int k = 120; k <= 123; k++){ pv[k][96 + m] = dp; }
  
  for (int k = 92; k <= 96; k++){ pv[k][97 + m] = lp; }
  for (int k = 103; k <= 107; k++){ pv[k][97 + m] = lp; }
  for (int k = 108; k <= 113; k++){ pv[k][97 + m] = lp; }
  for (int k = 130; k <= 133; k++){ pv[k][97 + m] = dp; }
  for (int k = 121; k <= 123; k++){ pv[k][97 + m] = dp; }
  
  for (int k = 93; k <= 97; k++){ pv[k][98 + m] = lp; }
  for (int k = 104; k <= 108; k++){ pv[k][98 + m] = lp; }
  for (int k = 111; k <= 112; k++){ pv[k][98 + m] = lp; }
  for (int k = 113; k <= 113; k++){ pv[k][98 + m] = dp; }
  for (int k = 126; k <= 127; k++){ pv[k][98 + m] = dp; }
  for (int k = 130; k <= 133; k++){ pv[k][98 + m] = dp; }
  for (int k = 121; k <= 123; k++){ pv[k][98 + m] = dp; }
  
  for (int k = 94; k <= 97; k++){ pv[k][99 + m] = lp; }
  for (int k = 104; k <= 108; k++){ pv[k][99 + m] = lp; }
  for (int k = 113; k <= 113; k++){ pv[k][99 + m] = dp; }
  for (int k = 126; k <= 127; k++){ pv[k][99 + m] = dp; }
  for (int k = 130; k <= 133; k++){ pv[k][99 + m] = dp; }
  for (int k = 121; k <= 123; k++){ pv[k][99 + m] = dp; }
  for (int k = 117; k <= 119; k++){ pv[k][99 + m] = dp; }
  
  for (int k = 95; k <= 99; k++){ pv[k][100 + m] = lp; }
  for (int k = 104; k <= 109; k++){ pv[k][100 + m] = lp; }
  for (int k = 112; k <= 113; k++){ pv[k][100 + m] = dp; }
  for (int k = 125; k <= 127; k++){ pv[k][100 + m] = dp; }
  for (int k = 129; k <= 133; k++){ pv[k][100 + m] = dp; }
  for (int k = 117; k <= 123; k++){ pv[k][100 + m] = dp; }
  
  for (int k = 96; k <= 100; k++){ pv[k][101 + m] = lp; }
  for (int k = 105; k <= 106; k++){ pv[k][101 + m] = lp; }
  for (int k = 108; k <= 109; k++){ pv[k][101 + m] = lp; }
  for (int k = 107; k <= 107; k++){ pv[k][101 + m] = dp; }
  for (int k = 111; k <= 111; k++){ pv[k][101 + m] = lp; }
  for (int k = 112; k <= 113; k++){ pv[k][101 + m] = dp; }
  for (int k = 125; k <= 133; k++){ pv[k][101 + m] = dp; }
  for (int k = 117; k <= 123; k++){ pv[k][101 + m] = dp; }
  
  for (int k = 97; k <= 100; k++){ pv[k][102 + m] = lp; }
  for (int k = 106; k <= 106; k++){ pv[k][102 + m] = lp; }
  for (int k = 109; k <= 109; k++){ pv[k][102 + m] = lp; }
  for (int k = 107; k <= 108; k++){ pv[k][102 + m] = dp; }
  for (int k = 111; k <= 113; k++){ pv[k][102 + m] = dp; }
  for (int k = 125; k <= 133; k++){ pv[k][102 + m] = dp; }
  for (int k = 118; k <= 123; k++){ pv[k][102 + m] = dp; }
  
  for (int k = 97; k <= 102; k++){ pv[k][103 + m] = lp; }
  for (int k = 105; k <= 106; k++){ pv[k][103 + m] = lp; }
  for (int k = 109; k <= 109; k++){ pv[k][103 + m] = lp; }
  for (int k = 107; k <= 108; k++){ pv[k][103 + m] = dp; }
  for (int k = 112; k <= 113; k++){ pv[k][103 + m] = dp; }
  for (int k = 126; k <= 132; k++){ pv[k][103 + m] = dp; }
  for (int k = 119; k <= 119; k++){ pv[k][103 + m] = dp; }
  for (int k = 121; k <= 123; k++){ pv[k][103 + m] = dp; }
  
  for (int k = 98; k <= 99; k++){ pv[k][104 + m] = lp; }
  for (int k = 101; k <= 106; k++){ pv[k][104 + m] = lp; }
  for (int k = 109; k <= 109; k++){ pv[k][104 + m] = lp; }
  for (int k = 100; k <= 100; k++){ pv[k][104 + m] = dp; }
  for (int k = 107; k <= 108; k++){ pv[k][104 + m] = dp; }
  for (int k = 112; k <= 115; k++){ pv[k][104 + m] = dp; }
  for (int k = 126; k <= 132; k++){ pv[k][104 + m] = dp; }
  for (int k = 121; k <= 123; k++){ pv[k][104 + m] = dp; }
  
  for (int k = 98; k <= 100; k++){ pv[k][105 + m] = lp; }
  for (int k = 103; k <= 104; k++){ pv[k][105 + m] = lp; }
  for (int k = 109; k <= 109; k++){ pv[k][105 + m] = lp; }
  for (int k = 101; k <= 102; k++){ pv[k][105 + m] = dp; }
  for (int k = 105; k <= 108; k++){ pv[k][105 + m] = dp; }
  for (int k = 113; k <= 115; k++){ pv[k][105 + m] = dp; }
  for (int k = 127; k <= 132; k++){ pv[k][105 + m] = dp; }
  for (int k = 121; k <= 123; k++){ pv[k][105 + m] = dp; }
  
  for (int k = 99; k <= 101; k++){ pv[k][106 + m] = lp; }
  for (int k = 106; k <= 107; k++){ pv[k][106 + m] = lp; }
  for (int k = 102; k <= 105; k++){ pv[k][106 + m] = dp; }
  for (int k = 108; k <= 109; k++){ pv[k][106 + m] = dp; }
  for (int k = 113; k <= 115; k++){ pv[k][106 + m] = dp; }
  for (int k = 127; k <= 132; k++){ pv[k][106 + m] = dp; }
  for (int k = 120; k <= 123; k++){ pv[k][106 + m] = dp; }
  
  for (int j = 90; j <= 99; j++) {
    for (int k = 139; k <= 142; k++){ pv[k][j + m] = dp; }
  }
  
  for (int j = 85; j <= 102; j++) {
    for (int k = 145; k <= 148; k++){ pv[k][j + m] = dp; }
  }
  
  for (int k = 138; k <= 142; k++){ pv[k][100 + m] = dp; }
  
  for (int k = 138; k <= 141; k++){ pv[k][101 + m] = dp; }
  
  for (int k = 137; k <= 141; k++){ pv[k][102 + m] = dp; }
  
  for (int k = 137; k <= 141; k++){ pv[k][103 + m] = dp; }
  
  for (int k = 137; k <= 140; k++){ pv[k][104 + m] = dp; }
  
  for (int k = 136; k <= 140; k++){ pv[k][105 + m] = dp; }
  
  for (int k = 136; k <= 139; k++){ pv[k][106 + m] = dp; }
  
  for (int k = 144; k <= 148; k++){ pv[k][103 + m] = dp; }
  for (int k = 144; k <= 148; k++){ pv[k][104 + m] = dp; }
  for (int k = 144; k <= 148; k++){ pv[k][105 + m] = dp; }
  for (int k = 143; k <= 148; k++){ pv[k][106 + m] = dp; }
  //for (int k = 143; k <= 148; k++){ pv[k][107 + m] = dp; }
}
  public void settings() {  size(900,600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "fishtank" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
