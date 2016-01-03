set terminal 'png'
set output 'filter-pareto.png'

plot "non-dominated.dat" with points, "dominated.dat" with points
