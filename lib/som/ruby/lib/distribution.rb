class RubySOM
  module Distribution
    
    def distribute(start_value, end_value, array_size, want_ints)
      diff = 1.0 * (end_value - start_value)
      n = [array_size-1, 1].max
      
      (0..(array_size-1)).map do |i|
          v = start_value + i * diff / n
          want_ints ? v.round : v
      end
    end
    
  end
end
