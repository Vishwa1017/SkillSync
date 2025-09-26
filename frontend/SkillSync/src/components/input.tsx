type InputProps = {
  label: string;
  type?: string;
  placeholder?: string;
  id?: string;
};

export default function input({
  label,
  type = "text",
  placeholder,
  id,
}: InputProps) {
  return (
    <div className="row mb-3 align-items-center">
      <label htmlFor={id} className="col-sm-4 col-form-label bold">
        {label}
      </label>

      <div className="col-sm-8">
        <input
          type={type}
          id={id}
          placeholder={placeholder}
          className="form-control"
        />
      </div>
    </div>
  );
}
