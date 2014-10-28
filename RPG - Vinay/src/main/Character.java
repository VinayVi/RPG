package main;

public abstract class Character {
	protected int x;
	protected int y;
	protected int tileSize;
	protected long lastHealed;
	protected double currentHealth;
	protected double maxHealth;
	protected double str;
	protected double agi;
	protected double dex;
	protected double fort;
	protected double luck;
	protected double damage;
	protected double dodge;
	protected double cdr;
	protected double crit;
	protected double tps; //seconds per tiles
	protected boolean moving;
	protected int state;
	protected String name;
	
	public Character() {
		tileSize = 48;
		tps = 0.25;
		moving = false;
		state = 1;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean moveUp() {
		if(moving)
			return false;
		new Thread(new Runnable() {
			@Override
			public void run() {
				moving = true;
				for(int i=0; i<48; i++) {
					y-=1;
					state = (i/12)%4;
					try {
						Thread.sleep((long) (tps*1000/tileSize));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				moving = false;
			}			
		}).start();
		return true;
	}
	
	public boolean moveDown() {
		if(moving)
			return false;
		new Thread(new Runnable() {
			@Override
			public void run() {
				moving = true;
				for(int i=0; i<48; i++) {
					y++;
					state = (i/12)%4;
					try {
						Thread.sleep((long) (tps*1000/tileSize));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				moving = false;					
			}			
		}).start();
		return true;
	}
	
	public boolean moveLeft() {
		if(moving)
			return false;
		new Thread(new Runnable() {
			@Override
			public void run() {
				moving = true;
				for(int i=0; i<48; i++) {
					x-=1;
					state = (i/12)%4;
					try {
						Thread.sleep((long) (tps*1000/tileSize));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				moving = false;					
			}
		}).start();
		return true;
	}
	
	public boolean moveRight() {
		if(moving)
			return false;
		new Thread(new Runnable() {
			@Override
			public void run() {
				moving = true;
				for(int i=0; i<48; i++) {
					x+=1;
					state = (i/12)%4;
					try {
						Thread.sleep((long) (tps*1000/tileSize));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				moving = false;
			}
			
		}).start();
		return true;
	}
}
