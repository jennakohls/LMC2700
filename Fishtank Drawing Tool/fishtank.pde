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

import java.util.concurrent.CopyOnWriteArrayList;

//matrix of colors, pv = "pixel value"
int[][] pv = new int[180][150];
//pixel size
int ps = 4;
//speed of fish
int step = ps;

//normal color defs
int black = color(0,0,0);
color gray = color(100,100,100);
int white = color(255,255,255);
int whitet = color(255,255,255,98);
int fishWhite = color(255,255,255, 90);
int fishRed = color(255,0,0, 90);
color red = color(255,0,0);
int fishBlue = color(0,0,255, 90);
color blue = color(0,0,255);
int fishGreen = color(0,255,0, 90);
int green = color(0,255,0);
int darkP = color(103,51,161);
//pinkCoral
int pink1 = color(183,41,239, 98);
//dark blue coral colors
//
int lblue = color(100,150,254,98);
int mblue = color(34,101,226, 98);
int dblue = color(52,50,90, 98);
int gblue = color(29,91,153, 98);
//purple coral colors
color lp = color(132, 57, 237);
color dp = color(107,52,170);
//colors with alpha values
int pink1t = color(183,41,239,98);
int lbluet = color(100,150,254,98);
int mbluet = color(34,101,226,98);
int dbluet = color(52,50,90,98);
int gbluet = color(29,91,153,98);
//food colors
color foodGreen = fishGreen;
color foodRed = fishRed;
color foodBlue = lbluet;

//init food type tracking
static CopyOnWriteArrayList<Food> foods = new CopyOnWriteArrayList();
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

Bubble[] bubbles = new Bubble[10]; 

//Bubble b0 = new Bubble(50,50);
//Bubble b1 = new Bubble(50,50);
//Bubble b2 = new Bubble(50,50);
//Bubble b3 = new Bubble(50,50);
//Bubble b4 = new Bubble(50,50);
//Bubble b5 = new Bubble(50,50);
//Bubble b1 = new Bubble(50,50);
//Bubble b1 = new Bubble(50,50);
//Bubble b1 = new Bubble(50,50);
//Bubble b1 = new Bubble(50,50);

void setup() {
  size(900,600);
  noStroke();
  initBG();
  foods.add(new Food(0, 0, activeFoodColor, false));
  for (int i = 0; i <bubbles.length; i++){
    bubbles[i] = new Bubble((int) (random(598)),(int) (random(720)));
  }
}
void draw() {
  frameRate(30);
  drawBG();
  for(Bubble b: bubbles){
    updateBubble(b);
  }
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

void updateFood(Food f) {
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

void updateBubble(Bubble b){
  //b.row -= 10;
  ////b.col += ((int) random(2) == 0) ? (-1 * 10) : 10;
  //b.col +=10;
  //if(b.row <= 0) b.row = 598;
  //if(b.col <=0 || b.col >= 720) b.col = 0;
  b.drawBubble();
}

boolean fishFoodCollision(Fish fish, Food food) {
  return (food.row >= fish.row && food.row <= fish.row + fish.wt && 
    food.col >= fish.col && food.col <= fish.col + fish.ht);
}

void updateFish(Fish f){
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

void mousePressed(){
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

void mouseReleased() {
  //move the fish after dragging
  for (int i = 0; i < fishes.length; i++) {
    fishes[i].locked = false;
    fishes[i].dontMove = false;
  }
}
