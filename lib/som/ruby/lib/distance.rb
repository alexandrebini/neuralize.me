class RubySOM
  module Distance
    
    def index_distance(closer, range)
      [ (closer[0]-range[0]).abs, (closer[1]-range[1]).abs ].max
    end
    
    def euclidean_distance(x, y)
      dist = 0
      x.each_index do |k|
        dist += (x[k]-y[k])**2
      end
      Math.sqrt(dist)
    end
    
    def closer(x, array)
      closer = [0, 0]
      array.each_index do |i|
        array[i].each_index do |j|
          p = [i, j]
          if array[i][j]==x
            closer = p
            break
          elsif euclidean_distance(x, array[p[0]][p[1]]) < euclidean_distance(x, array[closer[0]][closer[1]])
            closer = p
          end
        end
      end
      closer
    end
    
    def in_range?(x, closer, range)
      index_distance(closer, x) <= range
    end

  end
end
