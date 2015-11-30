set terminal 'png'
set output 'hc-pareto.png'

plot "hillclimbing.dat" with points, "data/best.randomAB100.tsp" with points
