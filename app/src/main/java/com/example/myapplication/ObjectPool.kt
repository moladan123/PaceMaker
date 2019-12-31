package com.example.myapplication

class ObjectPool{

    private val pool = HashMap<Int, ArrayList<GameObject>>()
    private val staticObjects = HashSet<Int>()

    /**
     * Adds the object into the current level
     */
    fun add(gameObject: GameObject) {
        val id = gameObject.imageID
        if (!pool.containsKey(id)) {
            pool[id] = ArrayList()
            if (gameObject.isStatic) { staticObjects.add(id) }
        }
        pool[id]!!.add(gameObject)
    }

    /**
     * Returns a list of ID's (all things currently on the screen)
     */
    fun getPoolIds(): Set<Int> {
        return pool.keys
    }

    /**
     * Get a list of all objects of a certain type
     */
    fun getObjects(objectId: Int): Iterable<GameObject> {
        return pool[objectId]!!
    }

    fun isStatic(objectId: Int): Boolean {
        return staticObjects.contains(objectId)
    }

    fun clear() {
        pool.clear()
        staticObjects.clear()

        // TODO("Unload any resources that are not used in the next level")
    }



}