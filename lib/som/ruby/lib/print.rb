class RubySOM
  module Print

    def array_out(array, decimal=2, tabs=0)
      st = ""
      array.each do |i|
        if i.class==Array
          #st += "\t"*tabs+array_out(i, decimal,tabs)
          st += "  |  "+array_out(i, decimal,tabs)
        else
          st += "%0.#{decimal}f " % i rescue st+=i+"\t"
        end
      end
      st
    end

    def matrix_out(array, decimal=2, tabs=0)
      st = ""
      array.each do |i|
        if i[0].class == Array
          i.each { |j| st += array_out(j, decimal, tabs) }
        else
          st += array_out(i, decimal, tabs)
        end
        st += "\n"
      end
      st
    end

    def colorized_array_out(color, array)
      colorize(array_out(array),color)
    end

    def colorized_out(array, closer, range)
      st = ""

      array.each_index do |i|
        array[i].each_index do |j|

          color=""
          p = [i,j]

          distance = index_distance(closer, p)
          if in_range?(p, closer, range)
            distance==0 ? color=@@green : color=@@yellow
          elsif distance==range+1
            color = @@pur
          end

          st += colorized_array_out(color, array[i][j])+"\t"
        end
        st +="\n"
      end

      puts st
    end

    def step_out(time, training_times, input, range, alpha)
      puts colorize("\n Step: #{time}/#{training_times} \t\t x: #{array_out(input)} \t\t neighborhood: #{range} \t\t alpha: #{alpha}", @@red)
    end

    def positions(positions, weights)
      array = Array.new(weights.size){ Array.new(weights[0].size){0} }

      positions.sort_by{ |a,b| [a.point.x,a.point.y] }.each do |i|
        puts "#{i.point.x},#{i.point.y}     #{array_out(i.vo,0)}    "
        array[i.point.x][i.point.y] = array_out(i.vo,0)
      end

      puts matrix_out(array, 0)
    end

    private
      @@green  = "\e[1m\e[32m"
      @@yellow = "\e[1m\e[33m"
      @@red    = "\e[1m\e[31m"
      @@pur    = "\e[1m\e[35m"
      def colorize(text, color_code) 
        "#{color_code}#{text}\e[0m"
      end
  end
end
