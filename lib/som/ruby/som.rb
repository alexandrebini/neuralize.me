require File.join(File.dirname(__FILE__), 'lib/print')
require File.join(File.dirname(__FILE__), 'lib/distance')
require File.join(File.dirname(__FILE__), 'lib/distribution')
require File.join(File.dirname(__FILE__), 'lib/train')
  
class RubySOM
  include RubySOM::Distance
  include RubySOM::Distribution
  include RubySOM::Print
  include RubySOM::Train
  
  attr_accessor :inputs, :training_times, :current_training_time
  attr_accessor :weights, :weight_lines, :weight_columns
  attr_accessor :ranges, :start_range, :end_range
  attr_accessor :alpha, :start_alpha, :end_alpha, :alphas
  attr_accessor :randomize_weights, :start_random_weights, :end_random_weights
  attr_accessor :results, :finished, :start_at, :end_at
  
  def initialize(options={})
    options.each{ |key, value| instance_variable_set("@#{key.to_s}",value) if respond_to?(key) }
    p self
    self
  end
  
  def start!
    treat_inputs
    create_weights
    
    @ranges = distribute(start_range, end_range, training_times, true)
    @alphas = distribute(start_alpha, end_alpha, training_times, false)
    
    @finished = false
    @start_at = Time.now
    @results = []
        
    train
  end
  
  
  class << self
    def generate
      som = RubySOM.new(
        :weight_lines => 10, :weight_columns => 10, 
        :training_times => 5, 
        :start_range => 5, :end_range => 1, 
        :start_alpha => 0.1, :end_alpha => 0.01,
        :inputs => [ [1,2,3], [3,4,5] ]
      )
    end
  end
  
end
