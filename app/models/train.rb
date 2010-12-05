class Train
  include MongoMapper::Document

  key :weight_lines, Integer
  key :weight_columns, Integer
  key :dataset_id
  key :training_times, Integer
  key :start_range, Integer
  key :end_range, Integer
  key :start_alpha, Float
  key :end_alpha, Float

  key :finished, Boolean
  key :results, Array

  key :randomize_weights, Boolean, :default => true
  key :start_random_weights, Float, :default => 0
  key :end_random_weights, Float, :default => 100
  
  key :current_training_time, Integer
  key :start_at, Time
  key :end_at, Time
  
  key :language, String
  key :thread_id, Integer
  
  timestamps!

  def start
    new_som = som
    new_thread = Thread.new do
      Thread.current[:som] = new_som
      #Thread.current[:som].start!
      sleep 60
    end
    update_attributes(:finished => false, :start_at => new_som.start_at, :thread_id => new_thread.object_id)
  end

  def update_positions
    if thr = thread
      som = thr[:som]
      update_attributes(:results => som.results, :current_training_time => som.current_training_time, :finished => som.finished, :end_at => som.end_at)
    end
  end

  private
    def som
      case language
      when 'java' then nil
      when 'c' then nil
      else
        RubySOM.new(som_attributes)
      end
    end
    
    def som_attributes
      {
        :inputs => Dataset.find(dataset_id).lines_to_matrix, :training_times => training_times, 
        :weight_lines => weight_lines, :weight_columns => weight_columns,
        :start_range => start_range, :end_range => end_range,
        :start_alpha => start_alpha, :end_alpha => end_alpha,
        :randomize_weights => randomize_weights, :start_random_weights => start_random_weights, :end_random_weights => end_random_weights
      }
    end
    
    def thread
      Thread.list.each do |thr|
        return thr if thr.object_id == thread_id
      end
    end

  class << self
    def generate
      Train.new(
        :weight_lines => 15, :weight_columns => 15, 
        :dataset_id => Dataset.first.id, :training_times => 500, 
        :start_range => 7, :end_range => 2, 
        :start_alpha => 0.1, :end_alpha => 0.01,
        :language => 'ruby'
      )
    end
  end
end
