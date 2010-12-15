class RubySOM
  module Train
    
    def treat_inputs
      @inputs.map! do |r|
        r.map! do |s|
          if s.to_s.match(/\A[+-]?\d+?(\.\d+)?\Z/) #is number
            s = s.to_f
          else
            s = s.unpack("C*").sum
          end
        end
      end
    end
    
    def create_weights
      z_size = @inputs.max{ |x,y| x.size <=> y.size }.size
      if @randomize_weights || @end_random_weights.blank? || @start_random_weights.blank?
        min_max = Array.new(z_size){ [] }

        z_size.times do |i|
          @inputs.size.times do |j|
            min_max[i][0] = [ min_max[i][0], @inputs[j][i] ].min rescue @inputs[j][i]
            min_max[i][1] = [ min_max[i][1], @inputs[j][i] ].max rescue @inputs[j][i]
          end
        end
        value_proc = Proc.new{ |i| rand(min_max[i][1] - min_max[i][0]) + min_max[i][0] }
      else
        value_proc = Proc.new{ |i| rand(@end_random_weights - @start_random_weights) + @start_random_weights }
      end
      
      @weights = Array.new(weight_columns) do
        Array.new(weight_lines) do
          Array.new(z_size) do |i| 
            value_proc.call(i)
          end
        end
      end
    end
    
    def train
      @training_times.times do |time|
        
        @alpha = @alphas[time]
        @range = @ranges[time]
        
        @inputs.each do |input|
          closer = closer(input, @weights)
          learn(input, closer)

          step_out(time, @training_times, input, @range, @alpha)
          colorized_out(@weights, closer, @range)
        end
        update_positions(time)
      end
      
      @finished = true
      @end_at = Time.now
    end
    
    def relative_alpha(closer, range)
      @alpha/(index_distance(closer, range)+1)
    end

    private
      def learn(input, closer)
        @weights.each_index do |i|
          @weights[i].each_index do |j|
            p = [i, j]

            if in_range?(p, closer, @range)
              @weights[i][j].each_index do |k|
                @weights[i][j][k] += relative_alpha(closer,p) * (input[k]-@weights[i][j][k])
              end
            elsif index_distance(closer, p) == @range+1
              @weights[i][j].each_index do |k|
                @weights[i][j][k] -= relative_alpha(closer,p) * (input[k]-@weights[i][j][k])
              end
            end

          end
        end
      end
      
      def update_positions(time)
        result = []
        
        @inputs.each_index do |i|
           point = closer(@inputs[i], @weights)
           result << { :index => i, :x => point[0], :y => point[1] }
        end
        
        @results << result
        @current_training_time = time
      end
    
  end
end
