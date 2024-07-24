//Hinamizawa's Variables:
int alchItemId = 554;
int sea_seed = 21490;
int boatId = 30919;
ItemComposition itemComp = client.getItemComposition(alchItemId);
int counter = v.getInventory().count(alchItemId);
TileItem find_it = v.getGroundItem().find(sea_seed);
int[] tick_manipulation = {1, 2};
WorldPoint wp1 = new WorldPoint(3732, 10269, 1);
WorldPoint wp2 = new WorldPoint(3732, 10271, 1);
WorldPoint wp3 = new WorldPoint(3731, 10270, 1);
WorldPoint out = new WorldPoint(3769, 3898, 0);
WorldPoint out_2 = new WorldPoint(3768, 3898, 0);
WorldPoint[] points = {wp1, wp2, wp3};
WorldPoint currentLocation = client.getLocalPlayer().getWorldLocation();

// The script itself
if ((currentLocation.equals(out) || currentLocation.equals(out_2)) && counter > 0) {
    GameObject boat = v.getGameObject().findNearest(boatId);
    if (boat != null) {
        int boatSceneX = boat.getSceneMinLocation().getX();
        int boatSceneY = boat.getSceneMinLocation().getY();
        v.invoke("Dive", "<col=ffff>Rowboat", boatId, 4, boatSceneX, boatSceneY, false);
    }
} else if (find_it != null && counter > 0 && !v.getWalking().isMoving()) {
    v.getGroundItem().take(sea_seed);
} else if ((currentLocation.equals(wp1) || currentLocation.equals(wp2) || currentLocation.equals(wp3)) && counter > 0 && !v.getWalking().isMoving()) {
    int randomTick = tick_manipulation[new Random().nextInt(tick_manipulation.length)];
    v.getCallbacks().afterTicks(randomTick, () -> {
        if (itemComp != null) {
            v.getCombat().spell(Spell.HIGH_LEVEL_ALCHEMY);
            v.invoke("Cast", "<col=00ff00>High Level Alchemy</col><col=ffffff> -> <col=ff9040>" + itemComp.getName() + "</col>", 0, 58, v.getInventory().slot(alchItemId), 9764864, false);
        }
    });
} else if ((!currentLocation.equals(wp1) && !currentLocation.equals(wp2) && !currentLocation.equals(wp3)) && find_it == null && counter > 0 && !v.getWalking().isMoving()) {
    WorldPoint selectedPoint = points[new Random().nextInt(points.length)];
    v.getWalking().walk(selectedPoint);
} else if (counter == 0) {
    v.getGame().logout();
}
