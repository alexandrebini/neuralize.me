class DatasetColumn
  include MongoMapper::Document

  key :title, String
  key :description, String
  key :included, Boolean, :default=>true
  key :dataset_id, ObjectId
  
  belongs_to :dataset
  
  def to_json(options = {})
    super()
  end
  
end
