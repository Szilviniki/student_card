"use client";
import { Card } from "react-bootstrap";
import { useState } from "react";

export default function OneCard({front, back}) {
    const [isFlipped, setIsFlipped] = useState(false);

    const flip = () => {
        setIsFlipped(!isFlipped);
    };

    return (
        <div
            className={`flip-card ${isFlipped ? "flipped" : ""}`}
            onClick={flip}
        >
            <div className="flip-card-inner">
                <Card className="flip-card-front">
                    <Card.Body>
                        <Card.Text>{front}</Card.Text>
                    </Card.Body>
                </Card>

                <Card className="flip-card-back">
                    <Card.Body>
                        <Card.Text>{back}</Card.Text>
                    </Card.Body>
                </Card>
            </div>
        </div>
    );
}
