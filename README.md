# EvolutionGenerator

##Starting paramters can be found in config file.
#Filelds in file are:
..*witdh - the map will have x coordinate from 0 to width
*height - the map will have y coordiante from 0 to height
*jungleRatio - percent of map surface which is jungle
*initialPlantsCount - number of plants placed on map at the beginnig of simulation. Half will be place in jungle and half in steppe
*initialAnialsCount - number of animals placed on map at the beginnig of simulation
*animalMoveCost - energy take from animal after move
*animalInitialEnergy - starting energy for each animal
*energyFromPlant - energy which animal obtain afeter eating plant
*plantsInJungleSpawn - number of plants spawning in jungle after each day
*plantsInSteppeSpawn - number of plants spawning in steppe after each day

# Evolution Simulator

## Config file parameters

- `width_in_tiles` - width of field in tiles
- `height_in_tiles` - height of field in tiles
- `forest_ratio` - floating point number form [0, 0.49] that defines size of forest on the map
- `animal_start_count` - initial number of animals on the map
- `plants_to_grow_in_forest` - how many plants will grow in forest each day
- `plants_to_grow_in_steppe` - how many palnts will grow not in forest each day
- `step_cost` - amount of energy that animals lose at each step
- `max_nutrition_value` - maximum energy that animal can get from eating plant
- `animal_max_energy` - maximum anergy animal can have
- `animal_min_energy_to_populate` - minimum energy enimal need to populate
- `animation_delay` - time between each frame (default: 100 ms)

To start the program you should pass config filename as argument `Program arguments` if you use `IntelliJ`
