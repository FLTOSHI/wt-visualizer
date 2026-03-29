CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE TABLE vehicles (
    id SERIAL PRIMARY KEY,
    identifier TEXT NOT NULL UNIQUE,
    country TEXT NOT NULL,
    vehicle_type TEXT NOT NULL,
    era INTEGER NOT NULL,
    
    -- БРы
    arcade_br NUMERIC(3, 1),
    realistic_br NUMERIC(3, 1),
    simulator_br NUMERIC(3, 1),

    -- Статусы
    is_premium BOOLEAN DEFAULT FALSE,
    is_squadron BOOLEAN DEFAULT FALSE,
    is_pack BOOLEAN DEFAULT FALSE,
    on_marketplace BOOLEAN DEFAULT FALSE,

    -- Данные
    economy_data JSONB,
    engine_data JSONB,
    crew_data JSONB,
    weapon_system JSONB,
    sensors_data JSONB,
    modifications JSONB,
    
    -- Визуал
    image_url TEXT,
    tech_image_url TEXT,

    last_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Индексы
CREATE INDEX idx_vehicles_weaponry ON vehicles USING GIN (weapon_system jsonb_path_ops);
CREATE INDEX idx_vehicles_identifier_trgm ON vehicles USING GIN (identifier gin_trgm_ops);
CREATE INDEX idx_vehicles_br_realistic ON vehicles (realistic_br);
CREATE INDEX idx_vehicles_country_type ON vehicles (country, vehicle_type);