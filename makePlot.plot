set terminal 'png'
set output 'parento.png'

plot "non-dominated.dat" with points, "dominated.dat" with points
